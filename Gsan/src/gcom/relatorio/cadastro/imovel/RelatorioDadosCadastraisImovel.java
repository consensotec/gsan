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
package gcom.relatorio.cadastro.imovel;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.bean.ConsultarClienteRelacaoClienteImovelHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDadosCadastraisImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioDadosCadastraisImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_CADASTRAIS_IMOVEL);
	}
	
	public Object executar() throws TarefaException {
		
		List<RelatorioDadosCadastraisImovelBean> relatorioBeans = new ArrayList<RelatorioDadosCadastraisImovelBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DADOS_CADASTRAIS_IMOVEL, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse m�todo cria o RelatorioBean atrav�s dos parametros
	 * enviado a este objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioDadosCadastraisImovelBean criarRelatorioBean() {
		
		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");
		
		Collection<ClienteImovel> colecaoClienteImovel = 
			(Collection<ClienteImovel>) getParametro("colecaoClienteImovel");

		Collection<ImovelSubcategoria> colecaoImovelSubCategoria = 
			(Collection<ImovelSubcategoria>) getParametro("colecaoImovelSubcategoria");

		RelatorioDadosCadastraisImovelBean relatorioBean = new RelatorioDadosCadastraisImovelBean();
		
		relatorioBean.setInscricaoImovel(consultarImovelForm.getMatriculaImovelDadosCadastrais());
		relatorioBean.setMatriculaImovel(consultarImovelForm.getIdImovelDadosCadastrais());
		relatorioBean.setEnderecoImovel(consultarImovelForm.getEnderecoImovelDadosCadastrais());
		relatorioBean.setSituacaoAguaImovel(consultarImovelForm.getSituacaoAguaDadosCadastrais());
		relatorioBean.setSituacaoEsgotoImovel(consultarImovelForm.getSituacaoEsgotoDadosCadastrais());
		relatorioBean.setPerfilImovel(consultarImovelForm.getImovelPerfilDadosCadastrais());
		relatorioBean.setTipoDespejo(consultarImovelForm.getDespejoDadosCadastrais());
		relatorioBean.setAreaConstruida(consultarImovelForm.getAreaConstruidaDadosDadosCadastrais());		
		relatorioBean.setTestadaLote(consultarImovelForm.getTestadaLoteDadosCadastrais());
		relatorioBean.setVolumeInferiorReservatorio(consultarImovelForm.getVolumeReservatorioInferiorDadosCadastrais());
		relatorioBean.setVolumeSuperiorReservatorio(consultarImovelForm.getVolumeReservatorioSuperiorDadosCadastrais());
		relatorioBean.setVolumePiscina(consultarImovelForm.getVolumePiscinaDadosCadastrais());
		relatorioBean.setFonteAbastecimento(consultarImovelForm.getFonteAbastecimentoDadosCadastrais());
		relatorioBean.setTipoPoco(consultarImovelForm.getPocoTipoDadosCadastrais());
		relatorioBean.setDivisaoEsgoto(consultarImovelForm.getDivisaoEsgotoDadosCadastrais());
		relatorioBean.setPavimentoRua(consultarImovelForm.getPavimentoRuaDadosCadastrais());
		relatorioBean.setPavimentoCalcada(consultarImovelForm.getPavimentoCalcadaDadosCadastrais());
		relatorioBean.setNumeroIptu(consultarImovelForm.getNumeroIptuDadosCadastrais());
		relatorioBean.setNumeroCompanhiaEletrica(consultarImovelForm.getNumeroCelpeDadosCadastrais());
		relatorioBean.setCoordenadaUTMX(consultarImovelForm.getCoordenadaXDadosCadastrais());
		relatorioBean.setCoordenadaUTMY(consultarImovelForm.getCoordenadaYDadosCadastrais());
		relatorioBean.setOcorrenciaCadastro(consultarImovelForm.getCadastroOcorrenciaDadosCadastrais());
		relatorioBean.setEloAnormalidade(consultarImovelForm.getEloAnormalidadeDadosCadastrais());
		relatorioBean.setIndicadorImovelCondominio(consultarImovelForm.getIndicadorImovelCondominioDadosCadastrais());
		relatorioBean.setMatriculaCondominio(consultarImovelForm.getImovelCondominioDadosCadastrais());
		relatorioBean.setMatriculaImovelPrincipal(consultarImovelForm.getImovelPrincipalDadosCadastrais());
		relatorioBean.setNumeroPontosUtilizacao(consultarImovelForm.getNumeroPontosUtilizacaoDadosCadastrais());
		relatorioBean.setNumeroMoradores(consultarImovelForm.getNumeroMoradoresDadosCadastrais());
		relatorioBean.setIndicadorJardim(consultarImovelForm.getJardimDadosCadastrais());		
		relatorioBean.setTipoHabitacao(consultarImovelForm.getTipoHabitacaoDadosCadastrais());
		relatorioBean.setTipoPropriedade(consultarImovelForm.getTipoPropriedadeDadosCadastrais());
		relatorioBean.setTipoConstrucao(consultarImovelForm.getTipoConstrucaoDadosCadastrais());
		relatorioBean.setTipoCobertura(consultarImovelForm.getTipoCoberturaDadosCadastrais());
		relatorioBean.setDistritoAbastecimento(consultarImovelForm.getDistritoOperacionalDadosCadastrais());
		relatorioBean.setTipoAreaQuadraDadosCadastrais(consultarImovelForm.getTipoAreaQuadraDadosCadastrais());

		if( !Util.isVazioOrNulo(colecaoClienteImovel)){			
			relatorioBean.setColecaoClienteImovel(
					new JRBeanCollectionDataSource( criarColecaoClienteImovelHelper(colecaoClienteImovel) ));
		}

		if( !Util.isVazioOrNulo(colecaoImovelSubCategoria)){

			relatorioBean.setColecaoImovelSubcategoriaHelper(
					new JRBeanCollectionDataSource( criarColecaoImovelSubcategoriaHelper(colecaoImovelSubCategoria) ));
		}

		return relatorioBean;
	}

	/**
	 * Esse m�todo cria uma cole��o de ImovelSubcategoriaHelper
	 * a partir da cole��o de ImovelSubcategoria.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<ImovelSubcategoriaHelper> criarColecaoImovelSubcategoriaHelper(
			Collection<ImovelSubcategoria> colecaoImovelSubCategoria) {
		
		ArrayList<ImovelSubcategoriaHelper> colecaoImovelSubCategoriaHelper = new ArrayList<ImovelSubcategoriaHelper>();

		for(ImovelSubcategoria subCategoria : colecaoImovelSubCategoria){
			ImovelSubcategoriaHelper helperTemp = new ImovelSubcategoriaHelper();
			helperTemp.setImovelSubcategoria(subCategoria);
			colecaoImovelSubCategoriaHelper.add(helperTemp);
		}
		return colecaoImovelSubCategoriaHelper;
	}

	/**
	 * Esse m�todo cria os ClienteImovelHelper
	 * a partir da cole��o de ClienteImovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Collection<ConsultarClienteRelacaoClienteImovelHelper> criarColecaoClienteImovelHelper(
			Collection<ClienteImovel> colecaoClienteImovel) {
		
		Collection<ConsultarClienteRelacaoClienteImovelHelper> colecaoHelpers = new ArrayList<ConsultarClienteRelacaoClienteImovelHelper>();

		for(ClienteImovel clienteImovel : colecaoClienteImovel){
			if(clienteImovel.getCliente()!=null 
					&& !Util.isVazioOrNulo(clienteImovel.getCliente().getClienteFones())){
				
				ClienteFone fonePadrao = (ClienteFone)clienteImovel.getCliente().getClienteFones().iterator().next();
				fonePadrao.setIndicadorTelefonePadrao((short)1);
			}
			
			ConsultarClienteRelacaoClienteImovelHelper clienteImovelHelper= new ConsultarClienteRelacaoClienteImovelHelper();
			clienteImovelHelper.setClienteImovel(clienteImovel);
			colecaoHelpers.add(clienteImovelHelper);
		}
		return colecaoHelpers;
	}

	
	/**
	 * Esse m�todo cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(consultarImovelForm.getIdImovelDadosCadastrais()) ){
			parametros.put("idImovelFiltro",consultarImovelForm.getIdImovelDadosCadastrais());
		}
		
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosCadastraisImovel", this);
	}

}