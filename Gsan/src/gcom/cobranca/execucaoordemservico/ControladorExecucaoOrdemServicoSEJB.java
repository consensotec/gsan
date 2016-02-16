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
package gcom.cobranca.execucaoordemservico;

import gcom.arrecadacao.IRepositorioArrecadacao;
import gcom.arrecadacao.RepositorioArrecadacaoHBM;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.contratoparcelamento.IRepositorioContratoParcelamento;
import gcom.cobranca.contratoparcelamento.RepositorioContratoParcelamentoHBM;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


/**
 * Defini��o da l�gica de neg�cio do Session Bean de ControladorExecucaoOrdemServico
 * 
 * @author Andr� Miranda
 * @date 13/11/2015
 */
public class ControladorExecucaoOrdemServicoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;

	protected SessionContext sessionContext;

	private IRepositorioContratoParcelamento repositorioContratoParcelamento;
	protected IRepositorioFaturamento repositorioFaturamento;
	protected IRepositorioArrecadacao repositorioArrecadacao;
	
	private IRepositorioExecucaoOrdemServico repositorioExecucaoOrdemServico;

	public void ejbCreate() throws CreateException {
		repositorioContratoParcelamento = RepositorioContratoParcelamentoHBM.getInstancia();
		
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();
		
		repositorioExecucaoOrdemServico = RepositorioExecucaoOrdemServicoHBM.getInstancia();
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
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 *  
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Empresa> validarEmpresaPrincipal(Usuario usuario) throws ControladorException {
		Collection<Empresa> colecaoEmpresa = new ArrayList<Empresa>();

		if (!usuario.getEmpresa().getIndicadorEmpresaPrincipal().equals(Empresa.INDICADOR_EMPRESA_PRINCIPAL)) {
			colecaoEmpresa.add(usuario.getEmpresa());
			return colecaoEmpresa;
		}

		FiltroEmpresa filtro = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtro.setConsultaSemLimites(true);
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoEmpresa = this.getControladorUtil().pesquisar(filtro, Empresa.class.getName());

		if (Util.isVazioOrNulo(colecaoEmpresa)) {
			throw new ControladorException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
		}

		return colecaoEmpresa;
	}

	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection buscarColecaoArquivoTextoOSCobrancaSmartphone()  throws ErroRepositorioException, ControladorException{
		
		Collection retornoQuery = new ArrayList();
		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> retorno = new ArrayList<ArquivoTxtOSCobrancaSmartphoneHelper>();

		try
		{
			retornoQuery = this.repositorioExecucaoOrdemServico.buscarColecaoArquivoTextoOSCobrancaSmartphone();
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		Iterator it = retornoQuery.iterator();
		while (it.hasNext()) {
			ArquivoTxtOSCobrancaSmartphoneHelper arq = new ArquivoTxtOSCobrancaSmartphoneHelper();

			Object[] obj = (Object[]) it.next();

			// 0
			arq.setIdArquivo((Integer) obj[0]);
			// 1
			arq.setIdLocalidade((Integer) obj[1]);
			// 2
			arq.setCodigoSetorComercialInicial((Integer) obj[2]);
			// 3
			arq.setCodigoSetorComercialFinal((Integer) obj[3]);
			// 4
			arq.setNumeroQuadraInicial((Integer) obj[4]);
			// 5
			arq.setNumeroQuadraFinal((Integer) obj[5]);
			// 6
			arq.setIdLeiturista((Integer) obj[6]);
			// 7
			arq.setNomeCliente((String) obj[7]);
			// 8
			arq.setNomeFuncionario((String) obj[8]);
			// 9
			arq.setIdSituacao((Integer) obj[9]);
			// 10
			arq.setDescricaoSituacao((String) obj[10]);
			// 11
			if (obj[11] != null) {
				arq.setImei((Long) obj[11]);
			}
			if (obj[12] != null) {
				arq.setQtdOrdemServico(((Integer) obj[12]).toString());
			}
			if (obj[13] != null) {
				arq.setQtdOSEncerradas(((Integer) obj[13]).toString());
			}

//			if (obj[14] != null) {
//				arq.setDtGeracao(Util.formatarData((Date) obj[14]));
//			}
//			arq.setMac((String) obj[15]);

			retorno.add(arq);
		}

		return retorno;
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
															   Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ControladorException {

		try 
		{
			if (idSituacaoLeituraNova.intValue() == SituacaoTransmissaoLeitura.LIBERADO	||
				idSituacaoLeituraNova.intValue() == SituacaoTransmissaoLeitura.EM_CAMPO) {
				
				for (ArquivoTxtOSCobrancaSmartphoneHelper arquivoTxtOSCobrancaSmartphoneHelper : colecaoArquivoTextoOSCobrancaSmartphone) {
					
					Collection<Integer> idsArquivosLeiturista = repositorioExecucaoOrdemServico.pesquisarArquivosEmAbertoPorLeiturista(arquivoTxtOSCobrancaSmartphoneHelper.getIdLeiturista());

					if (idsArquivosLeiturista != null && !idsArquivosLeiturista.isEmpty()) {
						throw new ControladorException("atencao.arquivo_txt_leiturista_nao_liberado");
					}
				}
			}

			repositorioExecucaoOrdemServico.atualizarListaArquivoTextoOSCobrancaSmartphone(colecaoArquivoTextoOSCobrancaSmartphone,
																						  idSituacaoLeituraNova, leiturista, date);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

}