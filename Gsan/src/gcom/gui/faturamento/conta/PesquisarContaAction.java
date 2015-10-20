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

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Realiza a pesquisa de contas de im�vel de acordo com os par�metros informados na p�gina 
 *
 * @author Pedro Alexandre
 * @date 02/03/2006
 */
public class PesquisarContaAction extends GcomAction {
	/**
	 * Pesquisa as contas existentes para o im�vel
	 *
	 * [UC0248] Pesquisar Contas do Im�vel
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Pedro Alexandre
	 * @date 02/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno para a tela de resultado da pesquisa de contas do im�vel 
		ActionForward retorno = actionMapping.findForward("listaConta");

		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o form de pesquisa de contas do im�vel
		PesquisarContaActionForm pesquisarContaActionForm = (PesquisarContaActionForm) actionForm;
		
		// Recupera os par�metros do form
		String idImovel = (String) pesquisarContaActionForm.getIdImovel();
		String referenciaContaInicial = (String) pesquisarContaActionForm.getReferenciaContaInicial();
		String referenciaContaFinal = (String) pesquisarContaActionForm.getReferenciaContaFinal();
		String dataEmissaoContaInicialEmString = (String) pesquisarContaActionForm.getDataEmissaoContaInicial();
		String dataEmissaoContaFinalEmString = (String) pesquisarContaActionForm.getDataEmissaoContaFinal();
		String dataVencimentoContaInicialEmString = (String) pesquisarContaActionForm.getDataVencimentoContaInicial();
		String dataVencimentoContaFinalEmString = (String) pesquisarContaActionForm.getDataVencimentoContaFinal();
		String[] idSituacaoConta = (String[]) pesquisarContaActionForm.getIdSituacaoConta();
		
		
		//Cria o filtro de conta e seta a ordena��o de resultado da pesquisa
		//pelo c�digo da conta
		FiltroConta filtroConta = new FiltroConta();
		filtroConta.setCampoOrderBy(FiltroConta.ID);

		//Cria flag que vai indicar se o usu�rio informou ao menos um par�metro para pesquisar
		boolean peloMenosUmParametroInformado = false;

		//Caso o usu�rio informou o c�digo do im�vel, pesquisa todas as contas relacionadas com o im�vel
		//caso contr�rio indica que o usu�rio n�o informou o im�vel
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Indica que o usu�rio informou um par�metro para pesquisar as contas do im�vel 
			//peloMenosUmParametroInformado = true;

			//Cria o fitro de im�vel, e seta no filtro quais objetos necess�rios para a pesquisa de im�vel
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
        	
        	//Seta o c�digo do im�vel no filtro 
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	
        	//Pesquisa o im�vel informado, no sistema 
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//Caso o im�vel informado pelo usu�rio n�o tenha sido encontrado no sistema
        	//caso contr�rio manda o c�digo do im�vel no request 
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "im�vel");
        	}else{
        		httpServletRequest.setAttribute("idImovel",idImovel);
        	}
        	
        	//Recupera o objeto im�vel da cole��o
        	Imovel objetoImovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);        	
        	
        	//Cria o filtro para recuperar o relacionamento entre cliente e im�vel,
        	//e seta os objetos necess�rios no retorno da pesquisa
        	FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
        	filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.FIM_RELACAO_MOTIVO));
        	
        	//Efetua a pesquisa do relacionamento entre cliente e im�vel
        	Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
        	
        	//Caso n�o exista nenhum cliente relacionado com o im�vel
        	if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "cliente do tipo usu�rio foi");
        	}
        	
        	
        	//Recupera o relacionamento entre cliente e im�vel da cole��o pesquisada 
        	ClienteImovel objetoClienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
        	        	
        	//Seta no form de pesquisar conta todos os dados de im�vel para exibi��o na p�gina de resultado da pesquisa
        	pesquisarContaActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
        	pesquisarContaActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());
        	pesquisarContaActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());        	
        	pesquisarContaActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
        	
			//Seta no filtro de conta o c�digo do im�vel informado pelo usu�rio
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, idImovel));
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Im�vel");
		}
		
		
		//Caso o usu�rio tenha informado a refer�ncia inicial da conta, pesquisa as contas entre a refer�ncia inicial e final, replicando 
		//a refer�ncia inicial na final caso essa n�o tenha sido informada
		//Caso contr�rio seta a refer�ncia final para vazio, e pesquisa as contas para todas as refer�ncias existentes
		if (referenciaContaInicial != null && !referenciaContaInicial.trim().equalsIgnoreCase("")) {
			
			//Formata a refer�ncia inicial informada, para o formato de ano e depois m�s
			referenciaContaInicial = Util.formatarMesAnoParaAnoMesSemBarra((String) pesquisarContaActionForm.getReferenciaContaInicial());
			
			//Caso a refer�ncia final n�o tenha sido informada , replica a refer�ncia inicial na final
			//Caso contr�rio, formata a refer�ncia final para ano e m�s
			if(referenciaContaFinal == null || referenciaContaFinal.trim().equalsIgnoreCase("")){
				//Replica a refer�ncia inicial na refer�ncia final
				referenciaContaFinal = referenciaContaInicial;
			}else{
				//Formata a refer�ncia final informada, para o formato de ano e depois m�s
				referenciaContaFinal = Util.formatarMesAnoParaAnoMesSemBarra((String) pesquisarContaActionForm.getReferenciaContaFinal());
				
				//[FS0002]Caso a refer�ncia final da conta seja anterior a inicial, levanta a exce��o para o
				//usu�rio indicando que a refer�ncia final � anterior a inicial
				if((new Integer(referenciaContaInicial)).intValue() > (new Integer (referenciaContaFinal)).intValue()){
					throw new ActionServletException("atencao.referenciafinal.menorque");
				}
			}
		} else{
			//Seta a refer�ncia final da conta para nula
			referenciaContaFinal = null; 
		}

		//Caso a refer�ncia final da conta esteja diferente de nulo,
		//Pesquisa as contas entre a refer�ncia inicial e final
		if (referenciaContaFinal != null && !referenciaContaFinal.trim().equalsIgnoreCase("")) {
			//Indica que o usu�rio informou um par�metro para pesquisar as contas do im�vel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar as contas no intervalo entre a refer�ncia inicial e a final
			filtroConta.adicionarParametro(new MaiorQue(FiltroConta.REFERENCIA, referenciaContaInicial, ParametroSimples.CONECTOR_AND));
			filtroConta.adicionarParametro(new MenorQue(FiltroConta.REFERENCIA, referenciaContaFinal));
		}

		
		//Cria o formato da data
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		//Cria as vari�veis que vai armazenar as datas inicial e final, da emiss�o das contas
		Date dataEmissaoContaInicial = null;
		Date dataEmissaoContaFinal = null;
		
		//Caso o usu�rio informou a data de emiss�o da conta inicial, pesquisa as contas do im�vel referentes a data de 
		//emiss�o da conta informada
		//Caso contr�rio, pesquisa as contas sem restri��o de data de emiss�o
		if (dataEmissaoContaInicialEmString != null && !dataEmissaoContaInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			//[FS0003] Valida a data de emiss�o da conta inicial
			try {
				dataEmissaoContaInicial = formato.parse(dataEmissaoContaInicialEmString);
			} catch (ParseException e) {
				throw new ActionServletException("atencao.dataemissaoinicial.invalida");
			}

			//Caso a data final de emiss�o da conta n�o foi informada, replica a data inicial na final
			//Caso contr�rio, formata a data final 
			if(dataEmissaoContaFinalEmString == null || dataEmissaoContaFinalEmString.trim().equalsIgnoreCase("")){
				dataEmissaoContaFinal = dataEmissaoContaInicial;
			}else{
				
				//[FS0003] Valida a data de emiss�o da conta final
				try {
					dataEmissaoContaFinal = formato.parse(dataEmissaoContaFinalEmString);
				} catch (ParseException e) {
					throw new ActionServletException("atencao.dataemissaofinal.invalida");
				}
				
				//[FS0004]Caso a data de emiss�o final da conta seja anterior a inicial 
				//levanta a exce��o para o usu�rio indicando que a data final � anterior a data inicial
				if(dataEmissaoContaFinal.before(dataEmissaoContaInicial)){
					throw new ActionServletException("atencao.dataemissaofinal.menorque");
				}
			}
		
		} else{
			//Seta para nula a data final de emiss�o da conta
			dataEmissaoContaFinalEmString = null;
		}

		//Caso a data de emiss�o final da conta esteja diferente de nulo,
		//Pesquisa as contas entre a data de emiss�o inicial e final
		if (dataEmissaoContaFinalEmString != null && !dataEmissaoContaFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//Indica que o usu�rio informou um par�metro para pesquisar as contas do im�vel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar as contas no intervalo entre a data de emiss�o inicial e a final
			filtroConta.adicionarParametro(new Intervalo(FiltroConta.DATA_EMISSAO, dataEmissaoContaInicial, dataEmissaoContaFinal));
		}
		
		
		//Cria as vari�veis que vai armazenar as datas inicial e final, de vencimento das contas
		Date dataVencimentoContaInicial = null;
		Date dataVencimentoContaFinal = null;
		
		//Caso o usu�rio informou a data de vencimento da conta inicial, pesquisa as contas do im�vel referentes a data de 
		//vencimento da conta informada
		//Caso contr�rio, pesquisa as contas sem restri��o de data de vencimento
		if (dataVencimentoContaInicialEmString != null && !dataVencimentoContaInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			//[FS0003] valida a data de vencimento da conta inicial
			try {
				dataVencimentoContaInicial = formato.parse(dataVencimentoContaInicialEmString);
			} catch (ParseException e) {
				throw new ActionServletException("atencao.datavencimentoinicial.invalida");
			}

			//Caso a data final de vencimento da conta n�o foi informada, replica a data inicial na final
			//Caso contr�rio, formata a data final 
			if(dataVencimentoContaFinalEmString == null || dataVencimentoContaFinalEmString.trim().equalsIgnoreCase("")){
				dataVencimentoContaFinal = dataVencimentoContaInicial;
			}else{
				//[FS0003] valida a data de vencimento da conta final
				try {
					dataVencimentoContaFinal = formato.parse(dataVencimentoContaFinalEmString);
				} catch (ParseException e) {
					throw new ActionServletException("atencao.datavencimentofinal.invalida");
				}
				
				//[FS0004]Caso a data de vencimento final da conta for anterior a inicial 
				if(dataVencimentoContaFinal.before(dataVencimentoContaInicial)){
					throw new ActionServletException("atencao.datavencimentofinal.menorque");
				}
			}
		
		} else{
			//Seta para nula a data final de vencimento da conta
			dataVencimentoContaFinalEmString = null;
		}

		//Caso a data de vencimento final da conta for diferente de nulo,
		//Pesquisa as contas entre a data de vencimento inicial e final
		if (dataVencimentoContaFinalEmString != null && !dataVencimentoContaFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//indica que o usu�rio informou um par�metro para pesquisar as contas do im�vel
			peloMenosUmParametroInformado = true;
			
			//Indica no filtro para pesquisar as contas no intervalo entre a data de vencimento inicial e a final
			filtroConta.adicionarParametro(new Intervalo(FiltroConta.DATA_VENCIMENTO, dataVencimentoContaInicial, dataVencimentoContaFinal));
		}
		
		
		//Caso o usu�rio informou alguma situa��o de conta 
		if(idSituacaoConta != null && idSituacaoConta.length >0){
			//Indica que o usu�rio informou um par�metro para pesquisar as contas do im�vel
			peloMenosUmParametroInformado = true;
			
			//La�o para incluir no filtro todas as situa��es de conta informadas pelo usu�rio para pesquisa 
			for(int i=0; i< idSituacaoConta.length; i++ ){
			  if(! (new Integer(idSituacaoConta[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				if( i == 0 ){
					filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoConta[i], ParametroSimples.CONECTOR_OR,idSituacaoConta.length ));	
				}else{
				  if( i  == (idSituacaoConta.length - 1) ){
				    filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoConta[i]));
				  }else{
				    filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoConta[i], ParametroSimples.CONECTOR_OR));
				  }
				}
			  }
			}
		}else if (sessao.getAttribute("situacaoConta") != null) {
			
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.NORMAL,ParametroSimples.CONECTOR_OR,3 ));
			
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.RETIFICADA));
			
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.INCLUIDA, ParametroSimples.CONECTOR_OR));
		}
		
		// [FS0006] Erro caso o usu�rio mandou pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}


		//Carrega no filtro os objetos necess�rios para pesquisa
		filtroConta.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

		//Pesquisa as contas do im�vel, com os par�metros indicados no filtro
		Collection colecaoContasImovel = null; // = fachada.pesquisar(filtroConta,Conta.class.getName());

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroConta, Conta.class.getName());
		colecaoContasImovel = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		
		//Caso nenhuma conta tenha sido encontrada com os par�metros indicados
		if (colecaoContasImovel == null || colecaoContasImovel.isEmpty()) {
			//[FS0008]Nenhuma conta cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Conta");
			
			//Caso o n� de contas retornadas pela pesquisa seja maior que o n� m�ximo de registros permitidos  
		} else if (colecaoContasImovel.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			//[FS0007]Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			//Coloca a cole��o de contas do im�vel pesquisadas na sess�o 
			sessao.setAttribute("colecaoContasImovel", colecaoContasImovel);
		}

		//Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
