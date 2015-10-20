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
package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Inst�ncia do formul�rio que est� sendo utilizado
		InserirContaActionForm inserirContaActionForm = (InserirContaActionForm) actionForm;

		// Recebendo os dados enviados pelo formul�rio
		String imovelIdJSP = inserirContaActionForm.getIdImovel();
		String mesAnoContaJSP = inserirContaActionForm.getMesAnoConta();
		String vencimentoContaJSP = inserirContaActionForm.getVencimentoConta();
		Integer situacaoAguaContaJSP = new Integer(inserirContaActionForm
				.getSituacaoAguaConta());
		
		Integer situacaoEsgotoContaJSP = new Integer(inserirContaActionForm
			.getSituacaoEsgotoConta());
		
		Integer motivoInclusaoContaJSP = new Integer(inserirContaActionForm
				.getMotivoInclusaoID());
		
		String consumoAguaJSP = inserirContaActionForm.getConsumoAgua();
		String consumoEsgotoJSP = inserirContaActionForm.getConsumoEsgoto();
		String percentualEsgotoJSP = inserirContaActionForm
				.getPercentualEsgoto();
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();

		// Carrega as cole��es de acordo com os objetos da sess�o
		Collection colecaoDebitoCobrado = new Vector();
		if (sessao.getAttribute("colecaoDebitoCobrado") != null) {
			colecaoDebitoCobrado = (Collection) sessao
					.getAttribute("colecaoDebitoCobrado");
		}
		
		
		Collection colecaoCategoriaOUSubcategoria = null;
		
		if (sessao.getAttribute("colecaoCategoria") != null){
			
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoCategoria");
			
			this.atualizarQuantidadeEconomiasCategoria(colecaoCategoriaOUSubcategoria, 
			httpServletRequest);
			
		}
		else{
			
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
			
			this.atualizarQuantidadeEconomiasSubcategoria(colecaoCategoriaOUSubcategoria, 
			httpServletRequest);
			
		}
		
				
		// [FS0015] - Verificar se foi efetuado o c�lculo da conta

		//==============================================================================================
		Collection<CalcularValoresAguaEsgotoHelper> valoresConta = null;
		
		
			
		//[SF0001] - Determinar Valores para Faturamento de �gua e/ou Esgoto
		//********************************************************************
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		valoresConta = fachada.calcularValoresConta(mesAnoContaJSP, imovelIdJSP,
		situacaoAguaContaJSP, situacaoEsgotoContaJSP,
		colecaoCategoriaOUSubcategoria, consumoAguaJSP, consumoEsgotoJSP,
		percentualEsgotoJSP, null, usuarioLogado);
			
		
		//C�lcula o valor total dos d�bitos de uma conta de acordo com o informado pelo usu�rio
        BigDecimal valorTotalDebitosConta = fachada.calcularValorTotalDebitoConta(colecaoDebitoCobrado,
        httpServletRequest.getParameterMap());
		
		
		//Totalizando os valores de �gua e esgoto
        BigDecimal valorTotalAgua = new BigDecimal("0");
        BigDecimal valorTotalEsgoto = new BigDecimal("0");
        
        if (valoresConta != null && !valoresConta.isEmpty()){
        	
        	Iterator valoresContaIt = valoresConta.iterator();
        	CalcularValoresAguaEsgotoHelper valoresContaObjeto = null;
        	
        	while (valoresContaIt.hasNext()){
        		
        		valoresContaObjeto = (CalcularValoresAguaEsgotoHelper) valoresContaIt.next();
        		
        		//Valor Faturado de �gua
        		if (valoresContaObjeto.getValorFaturadoAguaCategoria() != null){
        			valorTotalAgua = valorTotalAgua.add(valoresContaObjeto.getValorFaturadoAguaCategoria());
        		}
        		
        		//Valor Faturado de Esgoto
        		if (valoresContaObjeto.getValorFaturadoEsgotoCategoria() != null){
        			valorTotalEsgoto = valorTotalEsgoto.add(valoresContaObjeto.getValorFaturadoEsgotoCategoria());
        		}
     
        	}
            
        }
        
        
        BigDecimal valorTotalConta = new BigDecimal("0.00");
        
        valorTotalConta = valorTotalConta.add(valorTotalAgua);
        valorTotalConta = valorTotalConta.add(valorTotalEsgoto);
        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);

        
        // [FS0008] - Verificar valor da conta igual a zero
		if (valorTotalConta.equals(new BigDecimal("0.00"))) {
			throw new ActionServletException("atencao.valor_conta_igual_zero");
		}
		//**************************************************************************************
		
		
		
		
		//Invertendo o formato para yyyyMM (sem a barra)
		mesAnoContaJSP = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoContaJSP);



		// Carregando as informa��es do im�vel
		FiltroImovel filtroImovel = new FiltroImovel();

		// Objetos que ser�o retornados pelo hibernate
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.setorComercial.localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("consumoTarifa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovelIdJSP));

		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel,
				Imovel.class.getName());

		// [FS0001] - Verificar exist�ncia da matr�cula do im�vel
		if (colecaoImovel == null || colecaoImovel.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"im�vel");
		}

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

		// LigacaoAguaSituacao
		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(situacaoAguaContaJSP);

		// LigacaoEsgotoSituacao
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacao.setId(situacaoEsgotoContaJSP);

		// Data de vencimento da conta
		// Para auxiliar na formata��o de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataVencimentoConta;

		try {
			dataVencimentoConta = formatoData.parse(vencimentoContaJSP);
		} catch (ParseException ex) {
			dataVencimentoConta = null;
		}

		// Motivo da inclus�o da conta
		ContaMotivoInclusao contaMotivoInclusao = new ContaMotivoInclusao();
		contaMotivoInclusao.setId(motivoInclusaoContaJSP);
		
		//CRC4202 - adicionado por Vivianne Sousa - 21/09/2010 - analista:Adriana Ribeiro
		Integer leituraAnterior = null;
		if(inserirContaActionForm.getLeituraAnteriorAgua() != null && !inserirContaActionForm.getLeituraAnteriorAgua().trim().equalsIgnoreCase("")) {	
			leituraAnterior = new Integer(inserirContaActionForm.getLeituraAnteriorAgua());
		}
		Integer leituraAtual = null;
		if(inserirContaActionForm.getLeituraAtualAgua() != null && !inserirContaActionForm.getLeituraAtualAgua().trim().equalsIgnoreCase("")) {	
			leituraAtual = new Integer(inserirContaActionForm.getLeituraAtualAgua());
		}
		
		
		// [SF002] - Gerar dados da conta, [SF003] - Gerar os d�bitos cobrados

        Integer idConta = fachada.inserirConta(new Integer(mesAnoContaJSP), imovel,
        colecaoDebitoCobrado, ligacaoAguaSituacao, ligacaoEsgotoSituacao,
        colecaoCategoriaOUSubcategoria, consumoAguaJSP, consumoEsgotoJSP,
        percentualEsgotoJSP, dataVencimentoConta, valoresConta,
        contaMotivoInclusao, requestMap, usuarioLogado,
        leituraAnterior,leituraAtual);
		

        
		montarPaginaSucesso(httpServletRequest, "Conta " + Util.formatarMesAnoReferencia(new Integer(mesAnoContaJSP).intValue())
				+ " do im�vel " + imovel.getId() + " inserida com sucesso.",
				"Inserir outra Conta",
				"exibirInserirContaAction.do?menu=sim",
				"exibirManterContaAction.do?idImovelRequest="
				+ imovel.getId() , "Atualizar Conta(s) do Imovel " + imovel.getId(), 
				"Emitir 2� Via de Conta", "gerarRelatorio2ViaContaAction.do?idConta="+ idConta);

		return retorno;
	}
	
	
	
	private Integer atualizarQuantidadeEconomiasCategoria(Collection colecaoCategorias, HttpServletRequest httpServletRequest){
		
		/*
		 * Atualizando a quantidade de economias por categoria de acordo com o
		 * informado pelo usu�rio
		 */ 
		
		Integer qtdEconnomia = 0;
		
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()){
			
			Iterator colecaoCategoriaIt = colecaoCategorias.iterator();
			Categoria categoria;
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			String qtdPorEconomia;
			
			while (colecaoCategoriaIt.hasNext()) {
				categoria = (Categoria) colecaoCategoriaIt.next();

				if (requestMap.get("categoria" + categoria.getId().intValue()) != null) {

					qtdPorEconomia = (requestMap.get("categoria"
							+ categoria.getId().intValue()))[0];

					if (qtdPorEconomia == null
							|| qtdPorEconomia.equalsIgnoreCase("")) {

						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Quantidade de economias");
					}

					categoria.setQuantidadeEconomiasCategoria(new Integer(
							qtdPorEconomia));
					
					qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
				}
			}
		}
		
		return qtdEconnomia;
	}
	
	
	
	private Integer atualizarQuantidadeEconomiasSubcategoria(Collection colecaoSubcategorias, HttpServletRequest httpServletRequest){
		
		/*
		 * Atualizando a quantidade de economias por subcategoria de acordo com o
		 * informado pelo usu�rio
		 */ 
		
		Integer qtdEconnomia = 0;
		
		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()){
			
			Iterator colecaoSubcategoriaIt = colecaoSubcategorias.iterator();
			Subcategoria subcategoria;
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			String qtdPorEconomia;
			
			while (colecaoSubcategoriaIt.hasNext()) {
				subcategoria = (Subcategoria) colecaoSubcategoriaIt.next();

				if (requestMap.get("subcategoria" + subcategoria.getId().intValue()) != null) {

					qtdPorEconomia = (requestMap.get("subcategoria"
							+ subcategoria.getId().intValue()))[0];

					if (qtdPorEconomia == null
							|| qtdPorEconomia.equalsIgnoreCase("")) {

						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Quantidade de economias");
					}

					subcategoria.setQuantidadeEconomias(new Integer(
							qtdPorEconomia));
					
					qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
				}
			}
		}
		
		return qtdEconnomia;
	}
	
}
