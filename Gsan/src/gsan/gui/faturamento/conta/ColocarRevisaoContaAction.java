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
package gsan.gui.faturamento.conta;

import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.NegativadorMovimentoRegItem;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.conta.FiltroContaMotivoRevisao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.consumo.ConsumoAnormalidade;
import gsan.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gsan.micromedicao.consumo.ConsumoHistorico;
import gsan.micromedicao.consumo.FiltroConsumoAnormalidade;
import gsan.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gsan.micromedicao.consumo.FiltroConsumoHistorico;
import gsan.micromedicao.consumo.LigacaoTipo;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ColocarRevisaoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirColocarRevisaoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Inst�ncia do formul�rio que est� sendo utilizado
        ColocarRevisaoContaActionForm colocarRevisaoContaActionForm = (ColocarRevisaoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Contas selecionadas pelo usu�rio
        String identificadoresConta = colocarRevisaoContaActionForm.getContaSelected();
        
        String[] arrayIdentificadores = identificadoresConta.split(",");
        
        int flag = 0;
		Integer contaNaBase = null; 
		Conta conta = null;
		ConsumoHistorico consumoHistoricoAgua = null;
		ConsumoAnormalidade consumoAnormalidade = null;
		ConsumoAnormalidadeAcao consumoAnormalidadeAcao = null;
		
		for (int i = 0; i < arrayIdentificadores.length; i++) {
			// Carregando a conta que est� na base
			String dadosConta = arrayIdentificadores[i];
			String[] idUltimaAlteracao = dadosConta.split("-");
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			
			Calendar data = new GregorianCalendar();
			data.setTimeInMillis(new Long(idUltimaAlteracao[1].trim())
					.longValue());
			
			String time = formatter.format(data.getTime());
			
			// alterado para fazer a pesquisa por Hql e nao com filtro como estava sendo feita antes - Fernanda Paiva - 23/08/2006
			contaNaBase = fachada.pesquisarExistenciaContaParaConcorrencia(idUltimaAlteracao[0],time);
			
			
			// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 05/07/2010 
			// Analista :  Jeferson Pedrosa.
        	// [FS0007] - Validar data de vencimento.
        	// -------------------------------------------------------------------------------------------
        	
        	// E o usu�rio n�o tenha permiss�o especial.
			boolean temPermissaoParaColocarContaEmRevisao = 
				fachada.verificarPermissaoEspecial(PermissaoEspecial.ENVIAR_CONTA_PARA_REVISAO,	usuarioLogado);		
			
			if(!temPermissaoParaColocarContaEmRevisao){
								
				SistemaParametro sistemaParametro = 
					this.getFachada().pesquisarParametrosDoSistema();
				
				int qtdDiasAtrasoVencimentoConta = sistemaParametro.getNumeroDiasRevisaoComPermEspecial();
				
				conta = (Conta) Util.retonarObjetoDeColecao( fachada.obterConta(contaNaBase));
				
				// SB0005 3.1.3
				// verifica se a conta esta vencida
				if(Util.compararData(conta.getDataVencimentoConta(), new Date()) == -1){
					// obt�m a quantidade de dias de atraso.
					int qtdDiasVencidas = Util.obterQuantidadeDiasEntreDuasDatas(
							conta.getDataVencimentoConta(), new Date());
					
					if(qtdDiasVencidas > qtdDiasAtrasoVencimentoConta){
						// Realizar um reload na tela de manter conta
			        	httpServletRequest.setAttribute("reloadPage", "OK");
						throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_para_dias_atraso", 
								Util.formatarAnoMesParaMesAno(conta.getReferencia()));
					}
				}
						
				FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
	            filtroConsumoHistorico.adicionarParametro( 
	            		new ParametroSimples( FiltroConsumoHistorico.IMOVEL_ID, conta.getImovel().getId() )  );
	            filtroConsumoHistorico.adicionarParametro( 
	            		new ParametroSimples( FiltroConsumoHistorico.LIGACAO_TIPO_ID, LigacaoTipo.LIGACAO_AGUA  ) );
	            filtroConsumoHistorico.adicionarParametro( 
	            		new ParametroSimples( FiltroConsumoHistorico.ANO_MES_FATURAMENTO, conta.getReferencia() ) );
	            Collection<ConsumoHistorico> colConsumoHistorico = Fachada.getInstancia().pesquisar( 
	            		filtroConsumoHistorico, ConsumoHistorico.class.getName() );
	            consumoHistoricoAgua = ( ConsumoHistorico ) Util.retonarObjetoDeColecao( colConsumoHistorico );

	            if(consumoHistoricoAgua != null && consumoHistoricoAgua.getConsumoAnormalidade() != null){
	            	// pesquisa Consumo Anormalidade
		            FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
					filtroConsumoAnormalidade.adicionarParametro( 
							new ParametroSimples( FiltroConsumoAnormalidade.ID, 
									consumoHistoricoAgua.getConsumoAnormalidade()));
					
					Collection<ConsumoAnormalidade> colConsumoAnormalidade = 
						Fachada.getInstancia().pesquisar( filtroConsumoAnormalidade, 
								ConsumoAnormalidade.class.getName() );
					
					consumoAnormalidade = ( ConsumoAnormalidade ) Util.retonarObjetoDeColecao( colConsumoAnormalidade );
					
					if(consumoAnormalidade != null && consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial() != null
							&& consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial().toString().equalsIgnoreCase("1")){
						
						// SB0005 3.1.4	
						// verifica se o Consumo Anormalidade exige permiss�o Especial
						if(consumoAnormalidade.getId().toString().equals(ConsumoAnormalidade.BAIXO_CONSUMO.toString()) 
								|| consumoAnormalidade.getId().toString().equals(ConsumoAnormalidade.ESTOURO_CONSUMO.toString())
								|| consumoAnormalidade.getId().toString().equals(ConsumoAnormalidade.ALTO_CONSUMO.toString()) ){
							
							
							// pesquisa Consumo Anormalidade
				            FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
				            filtroConsumoAnormalidadeAcao.adicionarParametro( 
									new ParametroSimples( FiltroConsumoAnormalidadeAcao.CONSUMO_ANORMALIDADE, 
											consumoAnormalidade.getId()));
							
							Collection<ConsumoAnormalidadeAcao> colConsumoAnormalidadeAcao = 
								Fachada.getInstancia().pesquisar( filtroConsumoAnormalidadeAcao, 
										ConsumoAnormalidadeAcao.class.getName() );
							
							consumoAnormalidadeAcao = ( ConsumoAnormalidadeAcao ) Util.retonarObjetoDeColecao( colConsumoAnormalidadeAcao );
							
							// Realizar um reload na tela de manter conta
				        	httpServletRequest.setAttribute("reloadPage", "OK");
							throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_com_anormalidade",
									Util.formatarAnoMesParaMesAno(conta.getReferencia()), 
									consumoAnormalidadeAcao.getNumerofatorConsumoMes1().toString());
						}else{
							
							// Realizar um reload na tela de manter conta
				        	httpServletRequest.setAttribute("reloadPage", "OK");
							throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_anormalidade_sem_permissao",
									Util.formatarAnoMesParaMesAno(conta.getReferencia()));
							
						}
						
					}
	            }
	            /*
	             * Comentado por Hugo Leonardo
	             * Data: 09/08/2010
	             * Analista: Jeferson Pedrosa.
	             * CRC: 4478
	              
	            /*
	             * Comentado por Hugo Leonardo
	             * Data: 09/08/2010
	             * Analista: Jeferson Pedrosa.
	             * CRC: 4478
	              
	            // SB0005 3.1.4
	            // Caso a situa��o de debito e credito for diferente de "NORMAL"
	            if( conta.getDebitoCreditoSituacaoAtual() != null 
	            		&& !conta.getDebitoCreditoSituacaoAtual().getId().toString().equals("0")){
	            	
	            	// Realizar um reload na tela de manter conta
		        	httpServletRequest.setAttribute("reloadPage", "OK");
					throw new ActionServletException("atencao.necessario_permissao_especial_para_revisao_conta_com_situacao_debito_credito", 
							Util.formatarAnoMesParaMesAno(conta.getReferencia()));
	            	
	            }
	            */
			}
			
			
			// Verificar atualiza��o realizada antes por outro usu�rio
			
			if (contaNaBase == null || contaNaBase.equals("")) {
				httpServletRequest.setAttribute("reloadPage", "OK");
				flag = 1;
				sessao.setAttribute("erroConcorrencia","erroConcorrencia");
			}
		}

		//ContaMotivoRevisao contaMotivoRevisao = new ContaMotivoRevisao();
		//contaMotivoRevisao.setId(new Integer(colocarRevisaoContaActionForm.getMotivoRevisaoContaID()));

		
		//MotivoRevisaoConta selecinado pelo usu�rio
        FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
        filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID,colocarRevisaoContaActionForm.getMotivoRevisaoContaID()));
        Collection retornoBusca = fachada.pesquisar(filtroContaMotivoRevisao, ContaMotivoRevisao.class.getName());
        ContaMotivoRevisao contaMotivoRevisao = (ContaMotivoRevisao)Util.retonarObjetoDeColecao(retornoBusca);
        
           
        if (sessao.getAttribute("colecaoContaImovel") != null && (identificadoresConta != null &&
        	!identificadoresConta.equalsIgnoreCase("")) && flag == 0){
        
        	Collection<Conta> colecaoContaImovel = (Collection) sessao.getAttribute("colecaoContaImovel");
        	
            //Cancelando uma ou v�rias contas
        	fachada.colocarRevisaoConta(colecaoContaImovel, identificadoresConta, contaMotivoRevisao,
        	usuarioLogado);
        	
        	//Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
        
                
        return retorno;
    }
	
	
}

