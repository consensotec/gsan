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
package gcom.webservice.totem;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.FiltroPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteContaHistorico;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteConta;
import gcom.cadastro.cliente.FiltroClienteContaHistorico;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.FiltroCobrancaDocumentoItem;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.webservice.totem.helpers.HelperContaGerarExtratoWebService;
import gcom.webservice.totem.helpers.HelperContaListarContasWebService;
import gcom.webservice.totem.helpers.HelperContaListarDebitosWebService;
import gcom.webservice.totem.helpers.HelperDebitoGerarExtratoWebService;
import gcom.webservice.totem.helpers.HelperDebitoListarDebitosWebService;
import gcom.webservice.totem.helpers.HelperDetalheContaObterDadosContaWebService;
import gcom.webservice.totem.helpers.HelperImovelWebService;
import gcom.webservice.totem.helpers.RetornoGerarExtratoWebService;
import gcom.webservice.totem.helpers.RetornoInserirDebitoWebService;
import gcom.webservice.totem.helpers.RetornoListarContasWebService;
import gcom.webservice.totem.helpers.RetornoListarDebitosWebService;
import gcom.webservice.totem.helpers.RetornoObterDadosContaWebService;
import gcom.webservice.totem.helpers.RetornoVerificarImovelWebService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


/**
 * Definição da lógica de negócio do Session Bean de ControladorTotem
 * 
 * @author Bruno Barros
 * @date 09/12/2015
 */
@SuppressWarnings("unchecked")
public class ControladorTotemSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;

	protected SessionContext sessionContext;

	public void ejbCreate() throws CreateException {

	
	}

	public void ejbRemove() { }

	public void ejbActivate() { }

	public void ejbPassivate() { }

	/**
	 * Seta o valor de sessionContext
	 * @param sessionContext O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Retorna o ControladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {
		try {
			ControladorUtilLocalHome localHome = (ControladorUtilLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o ControladorFaturamento
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst?ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas ?
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o ControladorCobranca
	 */
	private ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a inst?ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas ?
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método insere um debito a cobrar referente segunda via de conta
	 * para um determinado imóvel. A geração está atrelada ao Mês / Ano de Faturamento
	 * atual de Sistema Parametros.
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idImovel - Id do imóvel ao qual a cobranca ta taxa de segunda via será atribuida
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 *  
	 * @return Objeto serializado com as informações para o retorno do webService
	 * 
	 * @throws ControladorException
	 */
	public RetornoInserirDebitoWebService gerarDebitoACobrarTaxaEmissaoContaTotem( String idImovel, String key ) throws ControladorException {
		SistemaParametro sp = this.getControladorUtil().pesquisarParametrosDoSistema();
		
        RetornoInserirDebitoWebService retorno = new RetornoInserirDebitoWebService();
        
        try{        
	        if (key.equals( ConstantesSistema.KEY_WEBSERVICE_CAER )) {
	        	
	        	if (!idImovel.isEmpty()) {
	        		
	        		retorno.setError(0);
	        		retorno.setKey(key);       		
	        		        		
	        		retorno.setId( getControladorFaturamento().gerarDebitoACobrarTaxaEmissaoConta( Integer.parseInt( idImovel ), sp.getAnoMesFaturamento() )+"" );        		
	        		
	        	} else{
	        		retorno.setError( 1 );
	        		retorno.setKey(key);
	        		retorno.setMsg( "Taxa de Emissão: Imovel inválido" );
	        	}
	        } else {
	    		retorno.setError( 1 );
        		retorno.setKey(key);
	    		retorno.setMsg( "Taxa de Emissão: Chave inválida" );
	        }
        } catch ( Exception e ){
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			retorno = new RetornoInserirDebitoWebService();
    		retorno.setError( 1 );  
    		retorno.setKey(key);
    		retorno.setMsg( "Taxa de Emissão: Erro ao Consultar" );			
        }
        
		return retorno;
	}
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método pesquisa os dados de um imóvel baseado na chave passada 
	 * como parâmetro para o servlet. A chave pode ser uma matricula, um
	 * cpf ou cpnj. O método irá identificar pelo tamanho da chave para 
	 * saber o tipo de pesquisa. Se for inferior a 10, por matricula, igual a
	 * 11 por cpf, igual 1 14 por CPNJ
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param chavePesquisa - String contendo uma matricula, um cpf ou um cnpj
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */
	public RetornoVerificarImovelWebService verificarImovelTotem( String chavePesquisa, String key ) throws ControladorException{
		
        RetornoVerificarImovelWebService retorno = new RetornoVerificarImovelWebService();
        
        try{
        
	        if (key.equals( ConstantesSistema.KEY_WEBSERVICE_CAER )) {        	
	        	
	        	if (!chavePesquisa.isEmpty()) {
	
	        		FiltroClienteImovel filtro = new FiltroClienteImovel();        		
	        		filtro.adicionarParametro( new ParametroNulo( FiltroClienteImovel.DATA_FIM_RELACAO ) );
	        		filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO ) );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.logradouro.municipio.unidadeFederacao" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.logradouroBairro.bairro.municipio.unidadeFederacao" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.logradouroBairro.logradouro.logradouroTipo" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.logradouroBairro.logradouro.logradouroTitulo" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.logradouroCep.cep" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.logradouroCep.logradouro.logradouroTipo" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.imovelPerfil" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.enderecoReferencia" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.ligacaoAguaSituacao" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.ligacaoEsgotoSituacao" );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "cliente" );        		        		
	        		
	        		if ( chavePesquisa.length() < 10 ){
	        			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.IMOVEL, chavePesquisa ) );
	        		} else if ( chavePesquisa.length() == 11 ){
	        			filtro.adicionarParametro( new ParametroSimples( "cliente.cpf", chavePesquisa ) );
	        		} else if ( chavePesquisa.length() == 14 ){
	        			filtro.adicionarParametro( new ParametroSimples( "cliente.cnpj", chavePesquisa ) );
	        		}
	        		
	        		Collection<ClienteImovel> colClienteImovel = Fachada.getInstancia().pesquisar( filtro, ClienteImovel.class.getName());
	        		
	        		if ( colClienteImovel != null && !colClienteImovel.isEmpty() ){        			
	            		SistemaParametro sp = Fachada.getInstancia().pesquisarParametrosDoSistema();
	            		
	        			retorno.setError(0);
	            		retorno.setKey(key);
	        			
		        		for (ClienteImovel clienteImovel : colClienteImovel) {
		        			
		        			HelperImovelWebService helper = new HelperImovelWebService();
		        			
		        			String endereco = "";
		        			
		        			if ( clienteImovel.getImovel().getLogradouroBairro().getLogradouro() != null ){
		        				
		        				String tipo = "";
		        				String titulo = "";
		        				String logradouro = " " + clienteImovel.getImovel().getLogradouroBairro().getLogradouro().getNome();
		        				String numero = "";
		        				
		        				if ( clienteImovel.getImovel().getNumeroImovel() != null ){
		        					numero = ", " + clienteImovel.getImovel().getNumeroImovel();
		        				}
		        				
		        				if ( clienteImovel.getImovel().getLogradouroBairro().getLogradouro().getLogradouroTipo() != null ){
		        					tipo = clienteImovel.getImovel().getLogradouroBairro().getLogradouro().getLogradouroTipo().getDescricao();
		        				}
		        				
		        				if ( clienteImovel.getImovel().getLogradouroBairro().getLogradouro().getLogradouroTitulo() != null ){
		        					titulo = " " + clienteImovel.getImovel().getLogradouroBairro().getLogradouro().getLogradouroTitulo().getDescricao(); 
		        				}
	        				
		        				endereco = ( tipo + titulo + logradouro + numero ).trim();		        				
		        			}
		        			
		        			helper.setMatricula( clienteImovel.getImovel().getId()+"" );
		        			helper.setEndereco( endereco );		        			
		                   	helper.setClienteUsuario( clienteImovel.getCliente().getNome() );
		                   	
		                   	helper.setCidade( 
		                   			clienteImovel.getImovel().getLogradouroBairro() != null && 
		                   			clienteImovel.getImovel().getLogradouroBairro().getBairro() != null &&
		                   			clienteImovel.getImovel().getLogradouroBairro().getBairro().getMunicipio() != null ? 
		                   					clienteImovel.getImovel().getLogradouroBairro().getBairro().getMunicipio().getNome() : "" );
		                   	
		                   	helper.setBairro( clienteImovel.getImovel().getLogradouroBairro().getBairro().getNome() );
		                   	
		                   	helper.setUf( 
		                   			clienteImovel.getImovel().getLogradouroBairro() != null && 
		                   			clienteImovel.getImovel().getLogradouroBairro().getBairro() != null &&
		                   			clienteImovel.getImovel().getLogradouroBairro().getBairro().getMunicipio() != null ?
		                   					clienteImovel.getImovel().getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla() : "" );
		                   	
		                   	helper.setCep( clienteImovel.getImovel().getLogradouroCep().getCep().getCepFormatado() );
		                   	helper.setComplemento( clienteImovel.getImovel().getComplementoEndereco() );
		                   	helper.setGerarDebito2Via( clienteImovel.getImovel().getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta()+"" );
		                   	helper.setValorTaxa( sp.getValorSegundaVia().floatValue()+"" );
		                   	
		                   	helper.setSituacaoAgua( clienteImovel.getImovel().getLigacaoAguaSituacao().getDescricao() );
		                   	helper.setSituacaoEsgoto( clienteImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao() );
		                   	
		                   	retorno.addImovel(helper);
		                }
	            	} else{
	            		retorno.setError( 1 );
		        		retorno.setKey(key);
	            		retorno.setMsg( "Verificar Imovel: Nenhum registro encontrado." );
	            	}	        		
	        	} else{
	        		retorno.setError( 1 );
	        		retorno.setKey(key);
	        		retorno.setMsg( "Verificar Imovel: Consulta inválida" );
	        	}
	        } else {
	    		retorno.setError( 1 );
        		retorno.setKey(key);
	    		retorno.setMsg( "Verificar Imovel: Chave inválida" );
	        }
        } catch ( Exception e ){
        	retorno = new RetornoVerificarImovelWebService();
			sessionContext.setRollbackOnly();
			e.printStackTrace();
    		retorno.setError( 1 );
    		retorno.setKey(key);
    		retorno.setMsg( "Verificar Imovel: Erro ao Consultar" );			
        }	        
        
        return retorno;
	}
	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método lista todas as contas em aberto para um determinado imóvel
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do Imovel para pesquisa dos débitos
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	
	public RetornoListarContasWebService listarContasTotem( String matricula, String key ) throws ControladorException{
		
        RetornoListarContasWebService retorno = new RetornoListarContasWebService();
        
        try{
        
	        if (key.equals( ConstantesSistema.KEY_WEBSERVICE_CAER )) {        	
	        	
	        	if (!matricula.isEmpty()) {
	        		
	        		retorno.setError(0);
	        		retorno.setKey(key);
	        		
					String referenciaInicial = "01/0001";
					String referenciaFinal = "12/9999";
					String dataVencimentoInicial = "01/01/0001";
					String dataVencimentoFinal = "31/12/9999";
	
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String mesInicial = referenciaInicial.substring(0, 2);
					String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
					String anoMesInicial = anoInicial + mesInicial;
					String mesFinal = referenciaFinal.substring(0, 2);
					String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
					String anoMesFinal = anoFinal + mesFinal;
	
					Date dataVencimentoDebitoI;
					Date dataVencimentoDebitoF;
	
					try {
						dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
					} catch (ParseException ex) {
						dataVencimentoDebitoI = null;
					}
					try {
						dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
					} catch (ParseException ex) {
						dataVencimentoDebitoF = null;
					}
	
					// seta valores constantes para chamar o metodo que consulta debitos do imovel
					Integer tipoImovel = new Integer(1);
					Integer indicadorPagamento = new Integer(1);
					Integer indicadorConta = new Integer(1);
					Integer indicadorDebito = new Integer(1);
					Integer indicadorCredito = new Integer(1);
					Integer indicadorNotas = new Integer(1);
					Integer indicadorGuias = new Integer(1);
					Integer indicadorAtualizar = new Integer(1);
	
					// Obtendo os débitos do imovel
					ObterDebitoImovelOuClienteHelper debitos = Fachada.getInstancia()
							.obterDebitoImovelOuCliente(tipoImovel.intValue(),
									matricula, null, null,
									anoMesInicial, anoMesFinal,
									dataVencimentoDebitoI,
									dataVencimentoDebitoF, indicadorPagamento
											.intValue(), indicadorConta
											.intValue(), indicadorDebito
											.intValue(), indicadorCredito
											.intValue(), indicadorNotas
											.intValue(), indicadorGuias
											.intValue(), indicadorAtualizar
											.intValue(), null,2);
	    			
	    			Collection<ContaValoresHelper> colContas = debitos.getColecaoContasValores();
	    			
	    			if ( !colContas.isEmpty() ){    			
		    			for (ContaValoresHelper contaValoresHelper : colContas) {
		    				
		    				Conta conta = contaValoresHelper.getConta();
							
		    				HelperContaListarContasWebService helper = new HelperContaListarContasWebService();
		    				
		    				helper.setIdConta( conta.getId()+"" );
		    				helper.setAnoMesReferencia( contaValoresHelper.getFormatarAnoMesParaMesAno() );
		    				helper.setDataVencimento( contaValoresHelper.getVencimentoFormatado() );
		    				helper.setValor( contaValoresHelper.getValorTotalConta()+"" );
		    				helper.setSituacao( conta.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao()+"" );
		    				retorno.addConta(helper);
						}
		    			
		    			retorno.setQtd( colContas.size() );
	    			} else {
	            		retorno.setError( 1 );     
		        		retorno.setKey(key);
	            		retorno.setMsg( "Listar Contas: Nenhum registro encontrado." );    				
	    			}
	    			
	        	} else{
	        		retorno.setError( 1 );
	        		retorno.setKey(key);
	        		retorno.setMsg( "Listar Contas: Consulta inválida" );
	        	}
	        } else {
	    		retorno.setError( 1 );
        		retorno.setKey(key);
	    		retorno.setMsg( "Listar Contas: Chave inválida" );
	        }
        } catch ( Exception e ){
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			retorno = new RetornoListarContasWebService();
    		retorno.setError( 1 );        		
    		retorno.setMsg( "Listar Contas: Erro ao Consultar" );			
        }    
        
        return retorno;		
	}
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método lista todos os débitos de um determinado imóvel
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do Imovel para pesquisa dos débitos
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */		
	public RetornoListarDebitosWebService listarDebitosTotem( String matricula, String key ) throws ControladorException{
		
		RetornoListarDebitosWebService retorno = new RetornoListarDebitosWebService();
		
		try{
	        if (key.equals( ConstantesSistema.KEY_WEBSERVICE_CAER )) {        	
	        	
	        	if (!matricula.isEmpty()) {
	        		
	        		retorno.setError(0);
	        		retorno.setKey(key);
	        		
					String referenciaInicial = "01/0001";
					String referenciaFinal = "12/9999";
					String dataVencimentoInicial = "01/01/0001";
					String dataVencimentoFinal = "31/12/9999";
	
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String mesInicial = referenciaInicial.substring(0, 2);
					String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
					String anoMesInicial = anoInicial + mesInicial;
					String mesFinal = referenciaFinal.substring(0, 2);
					String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
					String anoMesFinal = anoFinal + mesFinal;
	
					Date dataVencimentoDebitoI;
					Date dataVencimentoDebitoF;
	
					try {
						dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
					} catch (ParseException ex) {
						dataVencimentoDebitoI = null;
					}
					try {
						dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
					} catch (ParseException ex) {
						dataVencimentoDebitoF = null;
					}
	
					// seta valores constantes para chamar o metodo que consulta debitos do imovel
					Integer tipoImovel = new Integer(1);
					Integer indicadorPagamento = new Integer(1);
					Integer indicadorConta = new Integer(1);
					Integer indicadorDebito = new Integer(1);
					Integer indicadorCredito = new Integer(1);
					Integer indicadorNotas = new Integer(1);
					Integer indicadorGuias = new Integer(1);
					Integer indicadorAtualizar = new Integer(1);
	
					// Obtendo os débitos do imovel
					ObterDebitoImovelOuClienteHelper debitos = Fachada.getInstancia()
							.obterDebitoImovelOuCliente(tipoImovel.intValue(),
									matricula, null, null,
									anoMesInicial, anoMesFinal,
									dataVencimentoDebitoI,
									dataVencimentoDebitoF, indicadorPagamento
											.intValue(), indicadorConta
											.intValue(), indicadorDebito
											.intValue(), indicadorCredito
											.intValue(), indicadorNotas
											.intValue(), indicadorGuias
											.intValue(), indicadorAtualizar
											.intValue(), null,2);
	    			
	    			Collection<ContaValoresHelper> colContas = debitos.getColecaoContasValores();
	    			
	    			for (ContaValoresHelper contaValoresHelper : colContas) {
	    				
	    				Conta conta = contaValoresHelper.getConta();
						
	    				HelperContaListarDebitosWebService helper = new HelperContaListarDebitosWebService();
	    				
	    				helper.setIdConta( conta.getId()+"" );
	    				helper.setAnoMesReferencia( contaValoresHelper.getFormatarAnoMesParaMesAno() );
	    				helper.setDataVencimento( contaValoresHelper.getVencimentoFormatado() );
	    				helper.setValor( contaValoresHelper.getValorTotalConta()+"" );
	    				helper.setSituacao( conta.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao()+"" );
	    				helper.setValorAcrescimo(
	    					 Util.somaBigDecimal( Util.somaBigDecimal(contaValoresHelper.getValorMulta(), contaValoresHelper.getValorJurosMora()), contaValoresHelper.getValorAtualizacaoMonetaria() ) +"");
	    				
	    				retorno.addConta(helper);
					}
	    			
	    			Collection<DebitoACobrar> colDebitos = debitos.getColecaoDebitoACobrar();
	    			
	    			for (DebitoACobrar debito : colDebitos) {
	    				
	    				HelperDebitoListarDebitosWebService helper = new HelperDebitoListarDebitosWebService();
	    				
	    				helper.setIdDebito( debito.getId()+"" );
	    				helper.setTipo( debito.getDebitoTipo().getId()+"" );
	    				helper.setNome( debito.getDebitoTipo().getDescricao()+"" );
	    				helper.setValor( debito.getValorDebito()+"" );
	    				helper.setAnoMesReferencia( ( debito.getAnoMesReferenciaDebito() != null ? debito.getAnoMesReferenciaDebito()+"" : "" ) );
	    				helper.setAnoMesCobranca( debito.getAnoMesCobrancaDebito()+"" );
	    				helper.setAnoMesContabil( debito.getAnoMesReferenciaContabil()+"" );
	    				helper.setNumeroPrestacao( debito.getNumeroPrestacaoDebito()+"" );
	    				helper.setNumeroPrestacaoCobradas( debito.getNumeroPrestacaoCobradas()+"" );
	    				
	    				retorno.addDebito(helper);
					}
	    			
	    			retorno.setQtd( colDebitos.size() + colContas.size() );
	        	} else{
	        		retorno.setError( 1 );
	        		retorno.setKey(key);
	        		retorno.setMsg( "Listar Débitos: Consulta inválida" );
	        	}
	        } else {
	    		retorno.setError( 1 );
        		retorno.setKey(key);
	    		retorno.setMsg( "Listar Débitos: Chave inválida" );
	        }
        } catch ( Exception e ){
        	retorno = new RetornoListarDebitosWebService();
			sessionContext.setRollbackOnly();
			e.printStackTrace();
    		retorno.setError( 1 );
    		retorno.setKey(key);
    		retorno.setMsg( "Listar Débitos: Erro ao Consultar" );			
        }	        
        
        return retorno;
	}
	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método pesquisa os dados de uma conta. O método pesquisará tanto
	 * em conta como em conta histórico
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idConta - id da conta que será pesquisada.  
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */			
	public RetornoObterDadosContaWebService obterDadosContaTotem( String idConta, String key ) throws ControladorException{
        RetornoObterDadosContaWebService retorno = new RetornoObterDadosContaWebService();
        
        try{
        
	        if (key.equals( ConstantesSistema.KEY_WEBSERVICE_CAER )) {       	
	        	
	        	if (!idConta.isEmpty()) {
	        		
	        		retorno.setError(0);
	        		retorno.setKey(key);
	        		
	        		// Consulta primeiro em conta
	        		retorno = preencherDadosConta( idConta, key, retorno );
	        		
	        		if ( retorno.getId() == null ){
	        			// Se não achou, conta historico
	        			retorno = preencherDadosContaHistorico( idConta, key, retorno );
	        		}
	        		
	        		if ( retorno.getId() == null ){
	            		retorno.setError(1);
		        		retorno.setKey(key);
		        		retorno.setMsg( "Conta: Conta não encontrada" );
	        		}
	        	} else{
	        		retorno.setError( 1 );  
	        		retorno.setKey(key);
	        		retorno.setMsg( "Conta: Consulta inválida" );
	        	}
	        } else {
	    		retorno.setError( 1 );  
        		retorno.setKey(key);
	    		retorno.setMsg( "Conta: Chave inválida" );
	        }
        } catch ( Exception e ){
        	retorno = new RetornoObterDadosContaWebService();
			sessionContext.setRollbackOnly();
			e.printStackTrace();
    		retorno.setError( 1 );  
    		retorno.setKey(key);
    		retorno.setMsg( "Conta: Erro ao Consultar" );			
        }   
        
        return retorno;
	}
	
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método gera um extrato contendo todos os débitos
	 * do imóvel. No caso do totem sempre será cobrado a taxa de emissão
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param matricula - id do imóvel que terá o extrato de débitos gerado
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * @param String - ids das contas que irão compor o débito
	 * @param String - ids das debitos que irão compor o débito
	 * @param String - indicador que diz se os débitos serão atualizados
	 * @param String - indicador que diz se será cobrado pela emissão do extrato 
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */
	public RetornoGerarExtratoWebService gerarExtratoTotem( 
			String matricula, 
			String key, 
			String contas, 
			String debitos, 
			String icAcrescimosImpontualidade,
			String icCobrarEmissao) throws ControladorException{
        RetornoGerarExtratoWebService retorno = new RetornoGerarExtratoWebService();
        
        try{
        	
        	if (key.equals( ConstantesSistema.KEY_WEBSERVICE_CAER )) {        	
	        	
	        	if (!matricula.isEmpty()) {
	        		
	        		retorno.setError(0);
	        		retorno.setKey(key);	        		
	        		
	        		Usuario usuario = new Usuario();
	        		usuario.setId( 1 );
	        		
	        		CobrancaDocumento cd = this.getControladorCobranca().gerarExtratoDebito( 
	        				matricula, 
	        				key, 
	        				contas, 
	        				debitos, 
	        				icAcrescimosImpontualidade, 
	        				icCobrarEmissao, 
	        				usuario );
	        		
	        		BigDecimal valorTaxa = new BigDecimal( 0.00 );	        		
	        		Boolean incluirTaxaNoExtrato = ( icCobrarEmissao != null && icCobrarEmissao.equals( ConstantesSistema.SIM+"" ) ? true : false );
	        		
	        		if (incluirTaxaNoExtrato) {
	        			Imovel imovel = new Imovel();
	        			imovel.setId( new Integer ( matricula ) );
	        			
	        			
	        			valorTaxa = this.getControladorCobranca().obterValorTaxaDocumentoCobranca(imovel, Short.parseShort("3"));
	        		}

	        		
	        		retorno.setId( cd.getId()+"" );
	        		retorno.setIdImovel( matricula );
	        		retorno.setValorTotal( cd.getValorDocumento().floatValue() +"" );
	        		
	        		// Calculamos o valor Original do documento
	        		BigDecimal valorOriginal = 
	        				cd.getValorDocumento().subtract( 
	        						( valorTaxa ).subtract( 
	        								( cd.getValorImpostos() != null ? cd.getValorImpostos() : new BigDecimal( 0.00 ) ).subtract( 
	        										( cd.getValorAcrescimos() != null ? cd.getValorAcrescimos() : new BigDecimal( 0.00 ) ).add( 
	        												( cd.getValorDesconto() != null ? cd.getValorDesconto() : new BigDecimal( 0.00 ) ) ) ) ) );
	        		retorno.setValorDebito( 
	        				valorOriginal.floatValue() + "");
	        		
	        		retorno.setValorServico( cd.getValorAcrescimos().add( cd.getValorTaxa() )+ "" );
	        		retorno.setValorTaxa( cd.getValorTaxa()+"" );
	        		retorno.setValorImposto( ( cd.getValorImpostos() != null ? cd.getValorImpostos()+"" : "" ) );
	        		retorno.setValorDesconto( ( cd.getValorDesconto() != null ? cd.getValorDesconto()+"" : "" ) );
	        		//TODO verificar por que está mandando esse valor duas vezes
	        		retorno.setValorAcrescimo( cd.getValorAcrescimos()+"" );        		
	        		retorno.setDataEmissao( Util.formatarData( cd.getEmissao() ) );
	        		retorno.setDataValidade( Util.formatarData( cd.getDataValidade() ) );
	        		
	        		// Pesquisamos o cliente
	        		FiltroClienteImovel filtro = new FiltroClienteImovel();        		
	        		filtro.adicionarParametro( new ParametroNulo( FiltroClienteImovel.DATA_FIM_RELACAO ) );
	        		filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.INDICADOR_NOME_CONTA, 1 ) );
	        		filtro.adicionarCaminhoParaCarregamentoEntidade( "cliente" );        		        		
	        		
        			filtro.adicionarParametro( new ParametroSimples( FiltroClienteImovel.IMOVEL, matricula ) );
	        		Collection<ClienteImovel> colClienteImovel = Fachada.getInstancia().pesquisar( filtro, ClienteImovel.class.getName());
	        		ClienteImovel clienteImovel = ( ClienteImovel ) Util.retonarObjetoDeColecao( colClienteImovel );
	        		Cliente cliente = clienteImovel.getCliente();
	        		
	        		retorno.setNomeCliente( cliente.getNome() );
	        		retorno.setCpfCnpj( cliente.getCnpj() != null ? cliente.getCnpj() : ( cliente.getCpf() != null ? cliente.getCpf() : "" ) );
	        		
	        		// Pesquisamos o imovel
	        		FiltroImovel filtroImovel = new FiltroImovel();        		
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "logradouro.municipio.unidadeFederacao" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "logradouroBairro.bairro.municipio.unidadeFederacao" );        		
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "logradouroCep.cep" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "logradouroCep.logradouro.logradouroTipo" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "setorComercial" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "quadra" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "imovelPerfil" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "enderecoReferencia" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "ligacaoAguaSituacao" );
	        		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "ligacaoEsgotoSituacao" );
	        		
	        		filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID, cd.getImovel().getId() ) );
	        		Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar( filtroImovel, Imovel.class.getName());
	        		
	        		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao( colImovel );
	        		
	    			retorno.setEndereco( imovel.getEnderecoFormatado() );
	               	retorno.setBairro( imovel.getLogradouroBairro().getBairro().getNome() );
	               	retorno.setCidade( 
	               			imovel.getLogradouro() != null && 
	               			imovel.getLogradouro().getMunicipio() != null ? 
	               					imovel.getLogradouro().getMunicipio().getNome() : "" );
	               	retorno.setCep( imovel.getLogradouroCep().getCep().getCepFormatado() );
	               	
	               	retorno.setUf( 
	               			imovel.getLogradouro() != null && 
	               			imovel.getLogradouro().getMunicipio() != null &&
	               			imovel.getLogradouro().getMunicipio().getUnidadeFederacao() != null ?
	               					imovel.getLogradouro().getMunicipio().getUnidadeFederacao().getSigla() : "" );
	               	
	               	retorno.setInscricao( imovel.getInscricaoFormatada() );
	               	retorno.setPerfilImovel( imovel.getImovelPerfil().getDescricao() );
	               	retorno.setSituacaoAgua( imovel.getLigacaoAguaSituacao().getDescricao() );
	               	retorno.setSituacaoEsgoto( imovel.getLigacaoEsgotoSituacao().getDescricao() );
	               	
	               	
	        		// Pesquisamos a quantidade de economias da principal categoria do imóvel
	               	FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
	        		filtroImovelSubCategoria.adicionarParametro( new ParametroSimples( FiltroImovelSubCategoria.IMOVEL_ID, imovel.getId() ) );
	        		filtroImovelSubCategoria.adicionarParametro( new ParametroSimples( FiltroImovelSubCategoria.IMOVEL_CATEGORIA, imovel.getCategoriaPrincipalId() ) );
	        		Collection<ImovelSubcategoria> colImovelSubCategoria = ( Collection<ImovelSubcategoria> ) Fachada.getInstancia().pesquisar(filtroImovelSubCategoria, ImovelSubcategoria.class.getName() );
	        		
	        		ImovelSubcategoria imovelSubCategoria = ( ImovelSubcategoria ) Util.retonarObjetoDeColecao( colImovelSubCategoria );
	               	
	               	if ( imovel.getCategoriaPrincipalId().intValue() == Categoria.RESIDENCIAL_INT ){
	               		retorno.setEconomias1( imovelSubCategoria.getQuantidadeEconomias()+"" );
	               	} else if ( imovel.getCategoriaPrincipalId().intValue() == Categoria.COMERCIAL_INT ){
	               		retorno.setEconomias2( imovelSubCategoria.getQuantidadeEconomias()+"" );               		               		
	               	} else if ( imovel.getCategoriaPrincipalId().intValue() == Categoria.INDUSTRIAL_INT ){
	               		retorno.setEconomias3( imovelSubCategoria.getQuantidadeEconomias()+"" );
	               	} else if ( imovel.getCategoriaPrincipalId().intValue() == Categoria.PUBLICO_INT ){
	               		retorno.setEconomias4( imovelSubCategoria.getQuantidadeEconomias()+"" );
	               	}               	
	
	               	retorno.setCodigoBarras( Util.obterCodigoBarras( cd ) );
	        		
	        		FiltroCobrancaDocumentoItem fcdi = new FiltroCobrancaDocumentoItem();
	        		fcdi.adicionarCaminhoParaCarregamentoEntidade( "contaGeral.conta" );
	        		fcdi.adicionarCaminhoParaCarregamentoEntidade( "contaGeral.contaHistorico" );
	        		fcdi.adicionarCaminhoParaCarregamentoEntidade( "debitoACobrarGeral.debitoACobrar.debitoTipo" );
	        		fcdi.adicionarCaminhoParaCarregamentoEntidade( "debitoACobrarGeral.debitoACobrarHistorico.debitoTipo" );
	        		fcdi.adicionarParametro( new ParametroSimples( FiltroCobrancaDocumentoItem.COBRANCA_DOCUMENTO_ID, cd.getId() ) );
	        		fcdi.setCampoOrderBy( "contaGeral.conta.referencia", "contaGeral.contaHistorico.anoMesReferenciaConta" );
	        		Collection<CobrancaDocumentoItem> colCobrancaDocumentoItem = ( Collection<CobrancaDocumentoItem> ) Fachada.getInstancia().pesquisar(fcdi, CobrancaDocumentoItem.class.getName() );
	        		
	        		for (CobrancaDocumentoItem cobrancaDocumentoItem : colCobrancaDocumentoItem) {
						
	        			HelperContaGerarExtratoWebService helperConta = null;
	        			HelperDebitoGerarExtratoWebService helperDebito = null;
	        			
	        			Conta conta = null;
	        			ContaHistorico contaHistorico = null;
	        			
	        			if ( cobrancaDocumentoItem.getContaGeral() != null ){
	        				helperConta = new HelperContaGerarExtratoWebService();
	        				conta = cobrancaDocumentoItem.getContaGeral().getConta();
	        				contaHistorico = cobrancaDocumentoItem.getContaGeral().getContaHistorico();
	        			}
	        			
	        			DebitoACobrar dbac = null;
	        			DebitoACobrarHistorico dbacHistorico = null; 
	        					
	        			if ( cobrancaDocumentoItem.getDebitoACobrarGeral() != null ){
	        				helperDebito = new HelperDebitoGerarExtratoWebService();
	        				dbac = cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar();
	        				dbacHistorico = cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrarHistorico();
	        			}
	        			
	        			if ( conta != null ){	        				
	        				helperConta.setId( conta.getId() +"" );
	        				helperConta.setReferencia( Util.formatarAnoMesParaMesAno( conta.getReferencia() ) );
	        				helperConta.setDataVencimento( Util.formatarData( conta.getDataVencimentoConta() ) );
	        				helperConta.setValor( conta.getValorTotalConta() );	        				
	        			} else if ( contaHistorico != null ){
	        				helperConta.setId( contaHistorico.getId() +"" );
	        				helperConta.setReferencia( Util.formatarAnoMesParaMesAno( contaHistorico.getAnoMesReferenciaConta() ) );
	        				helperConta.setDataVencimento( Util.formatarData( contaHistorico.getDataVencimentoConta() ) );
	        				helperConta.setValor( contaHistorico.getValorTotal().floatValue()+"" );
	        			}
	        			
	        			if ( dbac != null ){
	        				
	        				helperDebito.setId( dbac.getId()+"" );
	        				helperDebito.setAnoMesCobranca( Util.formatarAnoMesParaMesAno( ( dbac.getAnoMesCobrancaDebito() != null ? dbac.getAnoMesCobrancaDebito()+"" : "" ) ) );
	        				helperDebito.setAnoMesReferencia( Util.formatarAnoMesParaMesAno( ( dbac.getAnoMesReferenciaContabil() != null ? dbac.getAnoMesReferenciaContabil()+"" : "" ) ) );
	        				helperDebito.setAnoMesReferencia( Util.formatarAnoMesParaMesAno( ( dbac.getAnoMesReferenciaDebito() != null ? dbac.getAnoMesReferenciaDebito()+"" : "" ) ) );
	        				helperDebito.setValor( dbac.getValorDebito().floatValue()+"" );
	        				helperDebito.setNome( dbac.getDebitoTipo().getDescricao() );
	        				helperDebito.setTipo( dbac.getDebitoTipo().getId() +"" );
	        				helperDebito.setNumeroPrestacao( dbac.getNumeroPrestacaoDebito()+"" );
	        				helperDebito.setNumeroPrestacaoCobradas( dbac.getNumeroPrestacaoCobradas()+"" );
	        				
	        			} else if ( dbacHistorico != null ){

	        				helperDebito.setId( dbacHistorico.getId()+"" );
	        				helperDebito.setAnoMesCobranca( Util.formatarAnoMesParaMesAno( ( dbacHistorico.getAnoMesCobrancaDebito() != null ? dbacHistorico.getAnoMesCobrancaDebito()+"" : "" ) ) );
	        				helperDebito.setAnoMesReferencia( Util.formatarAnoMesParaMesAno( ( dbacHistorico.getAnoMesReferenciaContabil() != null ? dbacHistorico.getAnoMesReferenciaContabil()+"" : "" ) ) );
	        				helperDebito.setAnoMesReferencia( Util.formatarAnoMesParaMesAno( ( dbacHistorico.getAnoMesReferenciaDebito() != null ? dbacHistorico.getAnoMesReferenciaDebito()+"" : "" ) ) );
	        				helperDebito.setValor( dbacHistorico.getValorDebito().floatValue()+"" );
	        				helperDebito.setNome( dbacHistorico.getDebitoTipo().getDescricao() );
	        				helperDebito.setTipo( dbacHistorico.getDebitoTipo().getId() +"" );
	        				helperDebito.setNumeroPrestacao( dbacHistorico.getPrestacaoDebito()+"" );
	        				helperDebito.setNumeroPrestacaoCobradas( dbacHistorico.getPrestacaoCobradas()+"" );	        				
	        			}
	        			
	        			if ( helperConta != null ){
	        				retorno.addConta( helperConta );
	        			}
	        			
	        			if ( helperDebito != null ){
	        				retorno.addDebito( helperDebito );	
	        			}	        			
					}	
	        		
	        	} else{
	        		retorno.setError( 1 );        		
	        		retorno.setKey(key);
	        		retorno.setMsg( "Gerar Extrato: Consulta inválida" );
	        	}
	        } else {
	    		retorno.setError( 1 );  
        		retorno.setKey(key);
	    		retorno.setMsg( "Gerar Extrato: Chave inválida" );
	        }
        } catch ( Exception e ){
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			retorno = new RetornoGerarExtratoWebService();
    		retorno.setError( 1 );  
    		retorno.setKey(key);
    		retorno.setMsg( "Gerar Extrato: Erro ao Consultar" );			
        }
        
        return retorno;
	}
	
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método auxiliar pesquisar os dados de uma conta
	 * preenchendo no objeto de retorno do webservice
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idConta - id da conta para pesquisar 
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	
	private RetornoObterDadosContaWebService preencherDadosConta( String idConta, String key, RetornoObterDadosContaWebService retorno ) throws ControladorException{
		// Consulta a conta
		FiltroConta filtro = new FiltroConta();
		filtro.adicionarParametro( new ParametroSimples( FiltroConta.ID, idConta ) );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.setorComercial" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.quadra" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( FiltroConta.ROTA );
		Collection<Conta> colConta = ( Collection<Conta> ) Fachada.getInstancia().pesquisar(filtro, Conta.class.getName() );
		Conta conta = ( Conta ) Util.retonarObjetoDeColecao( colConta );
		
		if ( conta != null ){        			
    		retorno.setId( conta.getId()+"" );
    		retorno.setIdImovel( conta.getImovel().getId()+"" );
    		retorno.setReferencia( conta.getReferenciaFormatada()+"" );
    		retorno.setDataVencimento( Util.formatarData( conta.getDataVencimentoConta()  ) );
    		retorno.setValorTotal( conta.getValorTotalConta() );
    		
    		// Consulta o pagamento
    		FiltroPagamento filtroP = new FiltroPagamento();
    		filtroP.adicionarParametro( new ParametroSimples( FiltroPagamento.CONTA_ID, idConta ) );
    		Collection<Pagamento> colPagamento = ( Collection<Pagamento> ) Fachada.getInstancia().pesquisar(filtroP, Pagamento.class.getName() );
    		
    		Pagamento pagamento = null;
    		PagamentoHistorico pagamentoH = null;
    		
    		if ( colPagamento != null && !colPagamento.isEmpty() ){
    			pagamento = ( Pagamento ) Util.retonarObjetoDeColecao( colPagamento );	
    			
    			retorno.setDataPagamento( Util.formatarData( pagamento.getDataPagamento() ) );
    		} else {
        		FiltroPagamentoHistorico filtroPH = new FiltroPagamentoHistorico();
        		filtroPH.adicionarParametro( new ParametroSimples( FiltroPagamentoHistorico.CONTA_ID, idConta ) );
        		Collection<PagamentoHistorico> colPagamentoHistorico = ( Collection<PagamentoHistorico> ) Fachada.getInstancia().pesquisar(filtroPH, PagamentoHistorico.class.getName() );
        		
        		if ( colPagamentoHistorico != null && !colPagamentoHistorico.isEmpty() ){
        			pagamentoH = ( PagamentoHistorico ) Util.retonarObjetoDeColecao( colPagamentoHistorico );
        			retorno.setDataPagamento( Util.formatarData( pagamentoH.getDataPagamento() ) );
        		}
    		}
    		
    		Categoria catg = ( Categoria ) this.getControladorUtil().pesquisar( Categoria.class, conta.getImovel().getCategoriaPrincipalId() );
    		
    		retorno.setVolumeFaturado( ( conta.getConsumoAgua() < 10 ? 10 : conta.getConsumoAgua() ) +"" );
    		
    		retorno.setValorAgua( conta.getValorAgua() +"" );
    		retorno.setValorEsgoto( conta.getValorEsgoto() +"" );
    		retorno.setValorDebitos( conta.getDebitos().floatValue() + "" );
    		retorno.setValorCreditos( conta.getValorCreditos() +"" );
    		retorno.setValorImpostos( conta.getValorImposto() +"" );
    		retorno.setPercEsgoto( conta.getPercentualEsgoto() +"" );
    		retorno.setInscricao( conta.getImovel().getInscricaoFormatada() );
    		retorno.setGrupo( ( conta.getFaturamentoGrupo() != null ? conta.getFaturamentoGrupo().getId() : "" ) +"" );
    		retorno.setRota( ( conta.getRota() != null ? conta.getRota().getId() : "" ) +"" );
    		retorno.setSeqRota( conta.getImovel().getNumeroSequencialRota()+"" );
    		retorno.setCategoria( catg.getDescricao() );
    		
    		FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
    		filtroImovelSubCategoria.adicionarParametro( new ParametroSimples( FiltroImovelSubCategoria.IMOVEL_ID, conta.getImovel().getId() ) );
    		filtroImovelSubCategoria.adicionarParametro( new ParametroSimples( FiltroImovelSubCategoria.IMOVEL_CATEGORIA, conta.getImovel().getCategoriaPrincipalId() ) );
    		Collection<ImovelSubcategoria> colImovelSubCategoria = ( Collection<ImovelSubcategoria> ) Fachada.getInstancia().pesquisar(filtroImovelSubCategoria, ImovelSubcategoria.class.getName() );
    		
    		ImovelSubcategoria imovelSubCategoria = ( ImovelSubcategoria ) Util.retonarObjetoDeColecao( colImovelSubCategoria );        		
    		
    		retorno.setEconomias( imovelSubCategoria.getQuantidadeEconomias()+"" );
    		
    		// Pesquisamos o cliente responsável
    		//ClienteConta clienteResponsavel =  fachada.pesquisarClienteConta( conta.getId(), Integer.parseInt( ClienteRelacaoTipo.USUARIO+"" ) );
    		FiltroClienteConta filtroClienteConta = new FiltroClienteConta();
    		filtroClienteConta.adicionarParametro( new ParametroSimples( FiltroClienteConta.CONTA_ID, conta.getId() ) );
    		filtroClienteConta.adicionarParametro( new ParametroSimples( FiltroClienteConta.INDICADOR_NOME_CONTA, ConstantesSistema.SIM ) );
    		filtroClienteConta.adicionarCaminhoParaCarregamentoEntidade( FiltroClienteConta.CLIENTE );
    		Collection<ClienteConta> colClienteConta = ( Collection<ClienteConta> ) Fachada.getInstancia().pesquisar(filtroClienteConta, ClienteConta.class.getName() );
    		ClienteConta clienteConta = ( ClienteConta ) Util.retonarObjetoDeColecao( colClienteConta );
    		
    		
    		retorno.setIdCliente( clienteConta.getCliente().getId()+"" );
    		retorno.setNomeCliente( clienteConta.getCliente().getNome() );
    		
    		// Pesquisamos o consumo histórico da conta
    		FiltroConsumoHistorico filtroCH = new FiltroConsumoHistorico();
    		filtroCH.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.IMOVEL_ID, conta.getImovel().getId() ) );
    		filtroCH.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.ANO_MES_FATURAMENTO, conta.getReferencia() ) );
    		filtroCH.adicionarCaminhoParaCarregamentoEntidade( FiltroConsumoHistorico.CONSUMO_TIPO );
    		
    		Collection<ConsumoHistorico> colConsumoHistorico = ( Collection<ConsumoHistorico> ) Fachada.getInstancia().pesquisar(filtroCH, ConsumoHistorico.class.getName() );
    		ConsumoHistorico consumoHistorico = ( ConsumoHistorico )Util.retonarObjetoDeColecao( colConsumoHistorico );
    		
    		if ( consumoHistorico != null ){   
    			
        		retorno.setMediaMes( consumoHistorico.getConsumoMedio()+"" );
        		retorno.setConsumoFaturado( consumoHistorico.getNumeroConsumoFaturadoMes()+"" );            		
        		retorno.setDescricaoConsumo( consumoHistorico.getConsumoTipo().getDescricao() );
        		retorno.setCodigoBarras( Util.obterCodigoBarras( conta ) );
        		
        		// Pequisamos medicao historico
        		FiltroMedicaoHistorico filtroMH = new FiltroMedicaoHistorico();
        		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.LIGACAO_AGUA_ID, conta.getImovel().getId() ) );
        		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, conta.getReferencia() ) );
        		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_AGUA ) );
        		
        		Collection<MedicaoHistorico> colMedicaoHistorico = ( Collection<MedicaoHistorico> ) Fachada.getInstancia().pesquisar(filtroMH, MedicaoHistorico.class.getName() );
        		
        		if (Util.isVazioOrNulo(colMedicaoHistorico)){
        			
        			// Pequisamos medicao historico
            		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.IMOVEL_ID, conta.getImovel().getId() ) );
            		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, conta.getReferencia() ) );
            		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_ESGOTO ) );
            		
            		colMedicaoHistorico = ( Collection<MedicaoHistorico> ) Fachada.getInstancia().pesquisar(filtroMH, MedicaoHistorico.class.getName() );
        		}
        		
        		MedicaoHistorico medicaoHistorico = ( MedicaoHistorico )Util.retonarObjetoDeColecao( colMedicaoHistorico );
    			
        		if ( medicaoHistorico != null ){        			
        			retorno.setDataLeituraAtual( Util.formatarData( medicaoHistorico.getDataLeituraAtualFaturamento() ) );
        			retorno.setDataLeituraAnterior( Util.formatarData( medicaoHistorico.getDataLeituraAnteriorFaturamento() ) );
        			retorno.setDiasConsumo( Util.obterQuantidadeDiasEntreDuasDatas( medicaoHistorico.getDataLeituraAnteriorFaturamento(), medicaoHistorico.getDataLeituraAtualFaturamento() ) +"");
        			retorno.setLeituraAtual( medicaoHistorico.getLeituraAtualFaturamento() +"" );
        			retorno.setLeituraAnterior( medicaoHistorico.getLeituraAnteriorFaturamento()+"" );
        			retorno.setConsumoMedido( medicaoHistorico.getNumeroConsumoMes()+"" );
        		}
        		
    		}
    		
    		
		}
		
		EmitirContaHelper emitirContaHelper = new EmitirContaHelper();
		emitirContaHelper.setIdConta( conta.getId() );
		emitirContaHelper.setValorAgua( conta.getValorAgua() );
		emitirContaHelper.setValorEsgoto( conta.getValorEsgoto() );
		emitirContaHelper.setValorCreditos( conta.getValorCreditos() );
		emitirContaHelper.setValorImpostos( conta.getValorImposto() );
		emitirContaHelper.setDebitos( conta.getDebitos() );
		emitirContaHelper.setIdLigacaoAguaSituacao( conta.getLigacaoAguaSituacao().getId() );
		emitirContaHelper.setIdLigacaoEsgotoSituacao( conta.getLigacaoEsgotoSituacao().getId() );
		emitirContaHelper.setIdImovel( conta.getImovel().getId() );
		emitirContaHelper.setConsumoAgua( conta.getConsumoAgua() );
		emitirContaHelper.setConsumoEsgoto( conta.getConsumoEsgoto() );
		emitirContaHelper.setPercentualEsgotoConta( conta.getPercentualEsgoto() );
		
		String consumoRateio = "";
		
		
		// [SB0002] - Determinar tipo de ligação e tipo de Medição
		Integer[] parmSituacao = this.getControladorFaturamento().determinarTipoLigacaoMedicao(emitirContaHelper);
		Integer tipoMedicao = parmSituacao[1];
		
		Object[] parmsMedicaoHistorico = this.getControladorFaturamento().obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
		
		Collection<ContaLinhasDescricaoServicosTarifasTotalHelper> colecaoContaLinhasDescricaoServicosTarifasTotalHelper = this.getControladorFaturamento().gerarLinhasDescricaoServicoTarifasRelatorio(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
				tipoMedicao, false);
		
		for (ContaLinhasDescricaoServicosTarifasTotalHelper linha : colecaoContaLinhasDescricaoServicosTarifasTotalHelper) {
			HelperDetalheContaObterDadosContaWebService helper = new HelperDetalheContaObterDadosContaWebService();
			
			helper.setDescricao( linha.getDescricaoServicosTarifas() );
			helper.setValor(linha.getValor());
			helper.setFaixa( linha.getConsumoFaixa() );
			
			retorno.addConta( helper );
		}
		
		return retorno;
    }
    
	/**
	 * [0000] - Desenvolver 
	 * 
	 * Este método auxiliar pesquisar os dados de uma conta que está em histórico
	 * preenchendo no objeto de retorno do webservice
	 * 
	 * @author Bruno Barros
	 * @date 09/12/2015
	 * 
	 * @param idConta - id da conta historico para pesquisar 
	 * @param key - Chave passada para validar a chamada de todas as requisições do totem
	 * 
	 * @return Objeto serializado com as informações para o retorno do webService 
	 * 
	 * @throws ControladorException
	 */	 
	private RetornoObterDadosContaWebService preencherDadosContaHistorico( String idConta, String key, RetornoObterDadosContaWebService retorno ) throws ControladorException{
		// Consulta a conta
		FiltroContaHistorico filtro = new FiltroContaHistorico();
		filtro.adicionarParametro( new ParametroSimples( FiltroContaHistorico.ID, idConta ) );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.setorComercial" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel.quadra" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "rota" );
		Collection<ContaHistorico> colContaHistorico = ( Collection<ContaHistorico> ) Fachada.getInstancia().pesquisar(filtro, ContaHistorico.class.getName() );
		ContaHistorico contaHistorico = ( ContaHistorico ) Util.retonarObjetoDeColecao( colContaHistorico );
		
		if ( contaHistorico != null ){        			
    		retorno.setId( contaHistorico.getId()+"" );
    		retorno.setIdImovel( contaHistorico.getImovel().getId()+"" );
    		retorno.setReferencia( Util.formatarAnoMesParaMesAno( contaHistorico.getAnoMesReferenciaConta() ) );
    		retorno.setDataVencimento( Util.formatarData( contaHistorico.getDataVencimentoConta()  ) );
    		retorno.setValorTotal( contaHistorico.getValorTotalContaBigDecimal().floatValue() +"" );
    		
    		// Consulta o pagamento
    		FiltroPagamento filtroP = new FiltroPagamento();
    		filtroP.adicionarParametro( new ParametroSimples( FiltroPagamento.CONTA_ID, idConta ) );
    		Collection<Pagamento> colPagamento = ( Collection<Pagamento> ) Fachada.getInstancia().pesquisar(filtroP, Pagamento.class.getName() );
    		
    		Pagamento pagamento = null;
    		PagamentoHistorico pagamentoH = null;
    		
    		if ( colPagamento != null && !colPagamento.isEmpty() ){
    			pagamento = ( Pagamento ) Util.retonarObjetoDeColecao( colPagamento );	
    			
    			retorno.setDataPagamento( Util.formatarData( pagamento.getDataPagamento() ) );
    		} else {
        		FiltroPagamentoHistorico filtroPH = new FiltroPagamentoHistorico();
        		filtroPH.adicionarParametro( new ParametroSimples( FiltroPagamentoHistorico.CONTA_ID, idConta ) );
        		Collection<PagamentoHistorico> colPagamentoHistorico = ( Collection<PagamentoHistorico> ) Fachada.getInstancia().pesquisar(filtroPH, PagamentoHistorico.class.getName() );
        		
        		if ( colPagamentoHistorico != null && !colPagamentoHistorico.isEmpty() ){
        			pagamentoH = ( PagamentoHistorico ) Util.retonarObjetoDeColecao( colPagamentoHistorico );
        			retorno.setDataPagamento( Util.formatarData( pagamentoH.getDataPagamento() ) );
        		}
    		}   		
    		
    		Categoria catg = ( Categoria ) this.getControladorUtil().pesquisar( Categoria.class, contaHistorico.getImovel().getCategoriaPrincipalId() );
    		
    		retorno.setVolumeFaturado( ( contaHistorico.getConsumoAgua() < 10 ? 10 : contaHistorico.getConsumoAgua() ) +"" );
    		
    		retorno.setValorAgua( contaHistorico.getValorAgua() +"" );
    		retorno.setValorEsgoto( contaHistorico.getValorEsgoto() +"" );
    		retorno.setValorDebitos( contaHistorico.getValorDebitos() + "" );
    		retorno.setValorCreditos( contaHistorico.getValorCreditos() +"" );
    		retorno.setValorImpostos( contaHistorico.getValorImposto() +"" );
    		retorno.setPercEsgoto( contaHistorico.getPercentualEsgoto() +"" );
    		retorno.setInscricao( contaHistorico.getImovel().getInscricaoFormatada() );
    		retorno.setGrupo( contaHistorico.getFaturamentoGrupo().getId()+"" );
    		retorno.setRota( contaHistorico.getRota().getId()+"" );
    		retorno.setSeqRota( contaHistorico.getImovel().getNumeroSequencialRota()+"" );
    		retorno.setCategoria( catg.getDescricao() );
    		
    		FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
    		filtroImovelSubCategoria.adicionarParametro( new ParametroSimples( FiltroImovelSubCategoria.IMOVEL_ID, contaHistorico.getImovel().getId() ) );
    		filtroImovelSubCategoria.adicionarParametro( new ParametroSimples( FiltroImovelSubCategoria.IMOVEL_CATEGORIA, contaHistorico.getImovel().getCategoriaPrincipalId() ) );
    		Collection<ImovelSubcategoria> colImovelSubCategoria = ( Collection<ImovelSubcategoria> ) Fachada.getInstancia().pesquisar(filtroImovelSubCategoria, ImovelSubcategoria.class.getName() );
    		
    		ImovelSubcategoria imovelSubCategoria = ( ImovelSubcategoria ) Util.retonarObjetoDeColecao( colImovelSubCategoria );        		
    		
    		retorno.setEconomias( imovelSubCategoria.getQuantidadeEconomias()+"" );
    		
    		// Pesquisamos o cliente responsável
    		//ClienteConta clienteResponsavel =  fachada.pesquisarClienteConta( conta.getId(), Integer.parseInt( ClienteRelacaoTipo.USUARIO+"" ) );
    		FiltroClienteContaHistorico filtroClienteContaHistorico = new FiltroClienteContaHistorico();
    		filtroClienteContaHistorico.adicionarParametro( new ParametroSimples( FiltroClienteContaHistorico.CONTA_ID, contaHistorico.getId() ) );
    		filtroClienteContaHistorico.adicionarParametro( new ParametroSimples( FiltroClienteContaHistorico.INDICADOR_NOME_CONTA, ConstantesSistema.SIM ) );
    		filtroClienteContaHistorico.adicionarCaminhoParaCarregamentoEntidade( FiltroClienteContaHistorico.CLIENTE );
    		Collection<ClienteContaHistorico> colClienteContaHistorico = ( Collection<ClienteContaHistorico> ) Fachada.getInstancia().pesquisar(filtroClienteContaHistorico, ClienteContaHistorico.class.getName() );
    		ClienteContaHistorico clienteContaHistorico = ( ClienteContaHistorico ) Util.retonarObjetoDeColecao( colClienteContaHistorico );
    		
    		
    		retorno.setIdCliente( clienteContaHistorico.getCliente().getId()+"" );
    		retorno.setNomeCliente( clienteContaHistorico.getCliente().getNome() );
    		
    		// Pesquisamos o consumo histórico da conta
    		FiltroConsumoHistorico filtroCH = new FiltroConsumoHistorico();
    		filtroCH.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.IMOVEL_ID, contaHistorico.getImovel().getId() ) );
    		filtroCH.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.ANO_MES_FATURAMENTO, contaHistorico.getAnoMesReferenciaConta() ) );
    		filtroCH.adicionarCaminhoParaCarregamentoEntidade( FiltroConsumoHistorico.CONSUMO_TIPO );
    		
    		Collection<ConsumoHistorico> colConsumoHistorico = ( Collection<ConsumoHistorico> ) Fachada.getInstancia().pesquisar(filtroCH, ConsumoHistorico.class.getName() );
    		ConsumoHistorico consumoHistorico = ( ConsumoHistorico )Util.retonarObjetoDeColecao( colConsumoHistorico );
    		
    		if ( consumoHistorico != null ){   
    			
        		retorno.setMediaMes( consumoHistorico.getConsumoMedio()+"" );
        		retorno.setConsumoFaturado( consumoHistorico.getNumeroConsumoFaturadoMes()+"" );            		
        		retorno.setDescricaoConsumo( consumoHistorico.getConsumoTipo().getDescricao() );
        		
        		// Pequisamos medicao historico
        		FiltroMedicaoHistorico filtroMH = new FiltroMedicaoHistorico();
        		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.LIGACAO_AGUA_ID, contaHistorico.getImovel().getId() ) );
        		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, contaHistorico.getAnoMesReferenciaConta() ) );
        		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_AGUA ) );
        		
        		Collection<MedicaoHistorico> colMedicaoHistorico = ( Collection<MedicaoHistorico> ) Fachada.getInstancia().pesquisar(filtroMH, MedicaoHistorico.class.getName() );
        		
        		if (Util.isVazioOrNulo(colMedicaoHistorico)){
        			
        			// Pequisamos medicao historico
            		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.IMOVEL_ID, contaHistorico.getImovel().getId() ) );
            		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, contaHistorico.getAnoMesReferenciaConta() ) );
            		filtroMH.adicionarParametro( new ParametroSimples( FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_ESGOTO ) );
            		
            		colMedicaoHistorico = ( Collection<MedicaoHistorico> ) Fachada.getInstancia().pesquisar(filtroMH, MedicaoHistorico.class.getName() );
        		}
        		
        		MedicaoHistorico medicaoHistorico = ( MedicaoHistorico )Util.retonarObjetoDeColecao( colMedicaoHistorico );
    			
        		if ( medicaoHistorico != null ){        			
        			retorno.setDataLeituraAtual( Util.formatarData( medicaoHistorico.getDataLeituraAtualFaturamento() ) );
        			retorno.setDataLeituraAnterior( Util.formatarData( medicaoHistorico.getDataLeituraAnteriorFaturamento() ) );
        			retorno.setDiasConsumo( Util.obterQuantidadeDiasEntreDuasDatas( medicaoHistorico.getDataLeituraAnteriorFaturamento(), medicaoHistorico.getDataLeituraAtualFaturamento() ) +"");
        			retorno.setLeituraAtual( medicaoHistorico.getLeituraAtualFaturamento() +"" );
        			retorno.setLeituraAnterior( medicaoHistorico.getLeituraAnteriorFaturamento()+"" );
        			retorno.setConsumoMedido( medicaoHistorico.getNumeroConsumoMes()+"" );
        		}
        		
    		}
    		
		}
		
		EmitirContaHelper emitirContaHelper = new EmitirContaHelper();
		emitirContaHelper.setIdConta( contaHistorico.getId() );
		emitirContaHelper.setValorAgua( contaHistorico.getValorAgua() );
		emitirContaHelper.setValorEsgoto( contaHistorico.getValorEsgoto() );
		emitirContaHelper.setValorCreditos( contaHistorico.getValorCreditos() );
		emitirContaHelper.setValorImpostos( contaHistorico.getValorImposto() );
		emitirContaHelper.setDebitos( contaHistorico.getValorDebitos() );
		emitirContaHelper.setIdLigacaoAguaSituacao( contaHistorico.getLigacaoAguaSituacao().getId() );
		emitirContaHelper.setIdLigacaoEsgotoSituacao( contaHistorico.getLigacaoEsgotoSituacao().getId() );
		emitirContaHelper.setIdImovel( contaHistorico.getImovel().getId() );
		
		String consumoRateio = "";
		
		
		// [SB0002] - Determinar tipo de ligação e tipo de Medição
		Integer[] parmSituacao = this.getControladorFaturamento().determinarTipoLigacaoMedicao(emitirContaHelper);
		Integer tipoMedicao = parmSituacao[1];
		
		Object[] parmsMedicaoHistorico = this.getControladorFaturamento().obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
		
		Collection<ContaLinhasDescricaoServicosTarifasTotalHelper> colecaoContaLinhasDescricaoServicosTarifasTotalHelper = this.getControladorFaturamento().gerarLinhasDescricaoServicoTarifasRelatorio(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
				tipoMedicao, true);
		
		for (ContaLinhasDescricaoServicosTarifasTotalHelper linha : colecaoContaLinhasDescricaoServicosTarifasTotalHelper) {
			HelperDetalheContaObterDadosContaWebService helper = new HelperDetalheContaObterDadosContaWebService();
			
			helper.setDescricao( linha.getDescricaoServicosTarifas() );
			helper.setValor(linha.getValor());
			helper.setFaixa( linha.getConsumoFaixa() );
			
			retorno.addConta( helper );
		}
		
		return retorno;
    }
}