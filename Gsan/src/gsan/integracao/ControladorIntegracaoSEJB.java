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
* Thiago Silva Toscano de Brito
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
package gsan.integracao;

import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gsan.atendimentopublico.ordemservico.OrdemServicoMovimentoHistorico;
import gsan.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gsan.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.integracao.upa.OrdensServico;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.email.ErroEmailException;
import gsan.util.email.ServicosEmail;
import gsan.util.filtro.ConectorAnd;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


public class ControladorIntegracaoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;
	private IRepositorioIntegracao repositorioIntegracao = null;
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioIntegracao = RepositorioIntegracaoHBM.getInstancia();
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	
	//[UC0741]
	public void enviarMovimentoExportacaoFirma() {
		//selecionar todos os servi�os pertencentes a um movimento
		try {
			Collection ordensServicoMovimentoParaExportacao = repositorioIntegracao.pesquisarOrdemServicoMovimentoParaEnvioSAM();
			Collection ordensServicoParaExportacao = new ArrayList();
			Iterator iterator = ordensServicoMovimentoParaExportacao.iterator();
			
			while (iterator.hasNext()) {
				OrdemServicoMovimento movimento = (OrdemServicoMovimento) iterator.next();
				ordensServicoParaExportacao.add(montarOrdensServicoParaExportacao(movimento));
								
			}
			
			repositorioIntegracao.exportarOrdemServicoMovimentos(ordensServicoParaExportacao);
			
			//Reiniciar o iterator para atualizar as ordens enviadas somente no final
			iterator = ordensServicoMovimentoParaExportacao.iterator();
			while (iterator.hasNext()) {
				OrdemServicoMovimento movimento = (OrdemServicoMovimento) iterator.next();
			
				if (movimento.getIndicadorMovimento() == 1) {
					movimento.setIndicadorMovimento(new Short("2"));
					repositorioIntegracao.atualizarIndicadorMovimentoOrdemServicoMovimento(movimento);
				}
			
			}
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		}
		
		
	}
	
	private OrdensServico montarOrdensServicoParaExportacao(OrdemServicoMovimento ordemServicoMovimento) {
		
		OrdensServico ordensServico = new OrdensServico();
		
		ordensServico.setId(ordemServicoMovimento.getId());
		ordensServico.setOsUnidadeExecutora(ordemServicoMovimento.getUnidadeOrganizacionalExecutora().getId());
		ordensServico.setOsUnidadeCentralProgramacao(ordemServicoMovimento.getUnidadeOrganizacionalCentralizadora().getId());
		ordensServico.setOsDataInicio(ordemServicoMovimento.getDataTramite());
		ordensServico.setOsDataEncerramento(ordemServicoMovimento.getDataExecucao());
		ordensServico.setServicoId(ordemServicoMovimento.getServicoTipo().getId());
		ordensServico.setOsSolicitacao(""+ordemServicoMovimento.getRegistroAtendimento().getId());
		if (ordemServicoMovimento.getImovel() != null) {
			ordensServico.setOsMatriculaNumero(""+ordemServicoMovimento.getImovel().getId());
			ordensServico.setOsInscricao(ordemServicoMovimento.getImovel().getInscricaoFormatada());
		}
		
		ordensServico.setOsSolicitante(ordemServicoMovimento.getNomeSolicitante());
		ordensServico.setOsTelefoneSolicitante(ordemServicoMovimento.getTelefoneSolicitante());
		ordensServico.setOsEndereco(ordemServicoMovimento.getEndereco());
		ordensServico.setOsPontoReferencia(ordemServicoMovimento.getPontoReferencia());
		ordensServico.setOsBairro(ordemServicoMovimento.getBairro());
		ordensServico.setOsLocalidade(ordemServicoMovimento.getNomeLocalidade());
		
		String codigoElo = ""+ordemServicoMovimento.getCodigoElo();
		if (codigoElo.length() < 4) {
			codigoElo = Util.adicionarZerosEsquedaNumero(4,codigoElo);
			
		}
		
		ordensServico.setOsElo(codigoElo);
		
		String codigoSetor = ""+ordemServicoMovimento.getCodigoSetor();
		
		if (codigoSetor.length() < 4) {
			codigoSetor = Util.adicionarZerosEsquedaNumero(4,codigoSetor);
			
		}
		ordensServico.setOsSetor(codigoSetor);
		
		String numeroQuadra = ""+ordemServicoMovimento.getNumeroQuadra();
		
		if (numeroQuadra.length() < 4) {
			numeroQuadra = Util.adicionarZerosEsquedaNumero(4,numeroQuadra);
			
		}
		
		ordensServico.setOsQuadra(numeroQuadra);
		
		
		
		ordensServico.setOsObservacao(ordemServicoMovimento.getObservacao());
		ordensServico.setOsPrioridade(ordemServicoMovimento.getServicoTipoPrioridadeAtual().getId());
		ordensServico.setOsParecerTramite(ordemServicoMovimento.getParecer());
		if (ordemServicoMovimento.getPavimentoRua() != null) {
			ordensServico.setOsPavimentoRua(ordemServicoMovimento.getPavimentoRua().getId());
		}
		if (ordemServicoMovimento.getAreaPavimentoRua() != null) {
			ordensServico.setOsAreaPvtoRua(ordemServicoMovimento.getAreaPavimentoRua().doubleValue());
		}
		if (ordemServicoMovimento.getPavimentoCalcada() != null) {
			ordensServico.setOsPavimentoCalcada(ordemServicoMovimento.getPavimentoCalcada().getId());
		}
		if (ordemServicoMovimento.getAreaPavimentoCalcada() != null) {
			ordensServico.setOsAreaPvtoCalcada(ordemServicoMovimento.getAreaPavimentoCalcada().doubleValue());
		}
		
		ordensServico.setOsSituacaoData(ordensServico.getOsDataInicio());
		ordensServico.setOsSituacao('I');
		ordensServico.setOsMatriculaReferencia("S");
		ordensServico.setOsDataCadastro(ordensServico.getOsDataInicio());
		
		
		return ordensServico;
	}
	
	public void receberMovimentoExportacaoFirma() {
		//selecionar todos os servi�os pertencentes a um movimento
		
		HashMap<String, String> empresaOS = new HashMap<String, String>(); 
		
		try {
			Collection ordensServicoParaImportacao = repositorioIntegracao.pesquisarOrdensServicoParaRecebimentoUPA();
			Iterator iterator = ordensServicoParaImportacao.iterator();
			
			
			while (iterator.hasNext()) {
				OrdemServicoMovimento movimento = (OrdemServicoMovimento) iterator.next();
				
				String emailEmpresa = null;
				Empresa empresa = null;
				
				UnidadeOrganizacional unidadeRepavimentadora = movimento.getUnidadeOrganizacionalExecutora().getUnidadeRepavimentadora();
				
				if (unidadeRepavimentadora != null) {
					empresa = movimento.getUnidadeOrganizacionalExecutora().getUnidadeRepavimentadora().getEmpresa();
					emailEmpresa = empresa.getEmail();
				}
				
				if (emailEmpresa == null || emailEmpresa.trim().equals("")) {
					System.out.println("E-mail da empresa "+ (empresa != null ? empresa.getDescricao() : "") +" n�o cadastrado para envio de notifica��o");
					
				} else {
					String listaOS = empresaOS.get(emailEmpresa);
					if (listaOS == null) {
						listaOS = movimento.getId()+";"+movimento.getServicoTipo().getDescricao()+";"+getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(movimento.getRegistroAtendimento().getId())+",\n";
					} else {
						listaOS = listaOS + movimento.getId()+";"+movimento.getServicoTipo().getDescricao()+";"+getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(movimento.getRegistroAtendimento().getId())+",\n";
					}
					empresaOS.put(emailEmpresa, listaOS);
					
				} 
				
				//Encerrar Ordem Servico
				
				Usuario usuario = null;
				try {
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, movimento.getLoginUsuario()==null?"":movimento.getLoginUsuario()));
				usuario = (Usuario)getControladorUtil().pesquisar(filtroUsuario, Usuario.class.getName()).iterator().next();
				} catch (Exception e) {
					System.out.println("A ORDEM DE SERVICO: "+movimento.getId() + " nao possui usuario valido e nao teve a OS fechada");
					continue;
					
				}
				
				if (movimento.getDataExecucao().before(movimento.getDataGeracao())) {
					System.out.println("A ORDEM DE SERVICO: "+movimento.getId() + ": Instante de encerramento anterior � gera��o da ordem");
					continue;
				}
				
				if (movimento.getParecer() == null || movimento.getParecer().trim().equals("")) {
					movimento.setParecer("ENCERRAMENTO NORMAL, TRAMITE AUTOMATICO DE RETORNO DE TERCEIRA");
					
				}
				
				
/*				Integer numeroOS,
				Date dataEncerramento, Usuario usuarioLogado,
				String motivoEncerramento, Date ultimaAlteracao,
				String parecerEncerramento, String indicadorPavimento,
				String pavimento,
				Collection colecaoManterDadosAtividadesOrdemServicoHelper,
				IntegracaoComercialHelper integracaoComercialHelper,
				String tipoServicoOSId, OrdemServico osFiscalizacao,
				String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,OrdemServicoPavimento ordemServicoPavimento
*/	
				/*Alterado por: Anderson Italo
				 * data: 07/01/2010
				 * CRC3441 - Estava sendo passado a descri��o do motivo de encerramento ao inv�s do id,
				 * o que ocasionava erro no controladorOrdemServico na hora de atualizar a ordem de servi�o
				 * no m�todo que segue abaixo.
				 * */
				getControladorOrdemServico().encerrarOSComExecucaoSemReferencia(
						movimento.getId(), new Date(), usuario,
						""+movimento.getAtendimentoMotivoEncerramento().getId(), new Date(), ""+movimento.getParecer(), null, null, null, null,
						null, null, null, null,null,null,null,null);

				
				//alterado por Vivianne Sousa 20/06/2008
				//analista responsavel: Fabiola
				getControladorOrdemServico().tramitarOuEncerrarRADaOSEncerrada(
					    movimento.getId(),
					    usuario,
					    null,
					    ""+movimento.getRegistroAtendimento().getId(),
					    null);
				
				//Verificar se � necess�rio a abertura de ordem de servi�o de repavimenta��o
				if (movimento.getAtendimentoMotivoEncerramento().getIndicadorExecucao() == 1 && 
						movimento.getPavimentoRua() !=null && 
						movimento.getPavimentoRua().getId() != null && !movimento.getPavimentoRua().getId().toString().equals("0") && 
						movimento.getAreaPavimentoRua() != null &&  !movimento.getAreaPavimentoRua().toString().equals("0") ) {
					
					OrdemServico ordemServico = new OrdemServico();
					
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO, ConectorAnd.CONECTOR_AND));
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADORPAVIMENTO, ConstantesSistema.INDICADOR_USO_ATIVO));
					 
					   
					
					ordemServico.setServicoTipo((ServicoTipo)Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName())));
					ordemServico.setAreaPavimento(movimento.getAreaPavimentoRua());
					//ordemServico.setUnidadeAtual(unidadeRepavimentadora);
					ordemServico.setRegistroAtendimento(movimento.getRegistroAtendimento());
				
					Integer idOrdemServico = getControladorOrdemServico().gerarOrdemServico(ordemServico, usuario, null);
					ordemServico.setId(idOrdemServico);
				
					OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
					if ( movimento.getPavimentoRua() != null ) {
						
						UnidadeOrganizacional unidadeOrganizacional =  Fachada.getInstancia().obterUnidadeRepavimentadorAPartirMunicipio(ordemServico, "");
						
						if ( unidadeOrganizacional != null && unidadeOrganizacional.getId() != null ) { 
							
							ordemServicoPavimento.setUnidadeRepavimentadora(unidadeOrganizacional);
						}
						ordemServicoPavimento.setAreaPavimentoRua(movimento.getAreaPavimentoRua());
						ordemServicoPavimento.setPavimentoRua(movimento.getPavimentoRua());
						ordemServicoPavimento.setUltimaAlteracao(new Date());
					}
					
					getControladorOrdemServico()
						.encerrarOSComExecucaoSemReferencia(
							ordemServico.getId(),
							new Date(),
							usuario,
							"2",
							new Date(),
							ordemServico
									.getDescricaoParecerEncerramento(),
							"" + ConstantesSistema.INDICADOR_USO_ATIVO,
							movimento.getAreaPavimentoRua().toString(),
							null, null, null, null, null, null, ordemServicoPavimento,null,null,null);

				}
				
				
				movimento.setIndicadorMovimento(new Short("3"));
				getControladorUtil().atualizar(movimento);
				
				//Movendo para o hist�rico
				getControladorUtil().inserir(new OrdemServicoMovimentoHistorico(movimento));
				getControladorUtil().remover(movimento);
				
				
				
			}

			for (String emailEmpresa : empresaOS.keySet()) {

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_k:mm:ss");

				String assuntoEmail = "Conv�nio COMPESA - Demanda_"
						+ format.format(new Date());
				String textoEmail = "Prezado(s) Sr(s),\n\nSegue abaixo Rela��o com os n�meros de Ordens de Servi�os que comp�em esta demanda:\n\n"
						+ empresaOS.get(emailEmpresa)
						+ "\n\nAtenciosamente;\n\nGest�o Conv�nio de Repavimenta��o da COMPESA";
				ServicosEmail.enviarMensagem("GSAN", emailEmpresa,
						assuntoEmail, textoEmail);

			}
			//repositorioIntegracao.importarOrdensServico(ordensServicoMovimentoParaImportacao);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
		} catch (ErroEmailException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	public Integer gerarOS(Usuario usuario, OrdemServico ordemServico) throws ControladorException {
		Integer idOrdemServico = getControladorOrdemServico().gerarOrdemServico(ordemServico, usuario, null);
		return idOrdemServico;
	}

	

	/**
	 * Retorna o valor do ControladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @return O valor de ControladorOrdemServico
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		// pega a inst�ncia do ServiceLocator.
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
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
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {

		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ControladorException {
		try {
			return repositorioIntegracao.pesquisarHorarioProcessoIntegracaoUPA();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema",e);
		}
		
		
	}
	
}

