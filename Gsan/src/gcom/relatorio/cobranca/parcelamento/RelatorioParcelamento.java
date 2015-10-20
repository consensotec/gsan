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
* Mila Maria Coelho de Ara�jo
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
package gcom.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.ParcelamentoRelatorioHelper;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioParcelamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioParcelamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PARCELAMENTO);
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String idParcelamento = (String) getParametro("idParcelamento");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
		UnidadeOrganizacional unidadeUsuario = (UnidadeOrganizacional) getParametro("unidadeUsuario");
		Usuario usuarioLogado = (Usuario) getParametro("usuario");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		
		String descricaoUnidadeUsuario = "";
		if(unidadeUsuario != null && unidadeUsuario.getDescricao() != null){
			descricaoUnidadeUsuario = unidadeUsuario.getDescricao();
		}
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioParcelamentosBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioParcelamentoBean relatorioParcelamentoBean = null;

		ParcelamentoRelatorioHelper parcelamentoRelatorioHelper = fachada
				.pesquisarParcelamentoRelatorio(Integer.parseInt( idParcelamento ) );
		
		String idFuncionario = "";
		if (parcelamentoRelatorioHelper.getIdFuncionario() != null){
			idFuncionario = parcelamentoRelatorioHelper.getIdFuncionario().toString();
		}
		
		// se a cole��o de par�metros da analise n�o for vazia
		if (parcelamentoRelatorioHelper != null) {

			// Pega a Data Atual formatada da seguinte forma: dd de m�s(por extenso) de aaaa
			// Ex: 23 de maio de 1985
			/*DateFormat df = 
				DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
			
			String dataCorrente = df.format(new Date());*/
			
			String dataCorrente = Util.retornaDataPorExtenso(new Date());

			String idImovel = parcelamentoRelatorioHelper.getIdImovel().toString();
			
			String nomeCliente = "";
			
			if (parcelamentoRelatorioHelper.getNomeDevedor()!= null
					&& !parcelamentoRelatorioHelper.getNomeDevedor().equals("")){
				
				nomeCliente = parcelamentoRelatorioHelper.getNomeDevedor();
			}else{
				nomeCliente = parcelamentoRelatorioHelper.getNomeCliente();
			}
			
			String cpfCnpjCliente = "";
			if (parcelamentoRelatorioHelper.getCnpjDevedor()!= null
					&& !parcelamentoRelatorioHelper.getCnpjDevedor().equals("")){
				
				cpfCnpjCliente = parcelamentoRelatorioHelper.getCnpjDevedor();
			}else{
				cpfCnpjCliente = parcelamentoRelatorioHelper.getCpfCnpj();
			}

			Collection colecaoRelatorioParcelamentoDetalhamentosBeans = new ArrayList();
			
			// Guardaremos o maior e menor ano mes de referencia do documento processado
			Integer maiorAnoMesReferenciaDocumento = null;
			Integer menorAnoMesReferenciaDocumento = null;			
			            
             Collection colecaoRelatorioParcelamentoItens = fachada
                .pesquisarParcelamentoItemPorIdParcelamentoRelatorio( Integer.parseInt( idParcelamento ) );

            if (colecaoRelatorioParcelamentoItens != null && !colecaoRelatorioParcelamentoItens.isEmpty()) {
                Iterator colecaoRelatorioParcelamentoItensIterator = colecaoRelatorioParcelamentoItens.iterator();
    
                RelatorioParcelamentoDetalhamentoBean relatorioParcelamentoDetalhamentoBean = null;
    
                BigDecimal totalFaturas = new BigDecimal("0.00");
    
                BigDecimal totalServicos = new BigDecimal("0.00");
    
                BigDecimal totalGuias = new BigDecimal("0.00");
    
                BigDecimal totalCreditos = new BigDecimal("0.00");
    
                ParcelamentoItem parcelamentoItem = null;
                ParcelamentoItem parcelamentoItem2 = null;
    
                Object tipoAnterior = null;
                Object tipoAtual = null;
                
                while (colecaoRelatorioParcelamentoItensIterator.hasNext()) {
                    if (tipoAnterior == null) {
                        tipoAnterior = new Conta();
                    } else {
                        if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
                            tipoAnterior = parcelamentoItem.getContaGeral().getConta();
                        } else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
                            tipoAnterior = parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
                        } else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
                            tipoAnterior = parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
                        } else {
                            tipoAnterior = parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar();
                        }
                    }
    
                    if (parcelamentoItem2 != null && parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
                        parcelamentoItem = parcelamentoItem2;
                        parcelamentoItem2 = null;
                    } else {
    
                        parcelamentoItem = (ParcelamentoItem) colecaoRelatorioParcelamentoItensIterator.next();
                    }
    
                    if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
                        tipoAtual = parcelamentoItem.getContaGeral().getConta();
                    } else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
                        tipoAtual = parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
                    } else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
                        tipoAtual = parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
                    } else {
                        tipoAtual = parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getCreditoTipo();
                    }
    
                    if (tipoAnterior instanceof Conta) {
                        if (!(tipoAtual instanceof Conta)) {
                            relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                    // Matr�cula do Im�vel
                                    idImovel,
                                    // Nome do Cliente
                                    nomeCliente,
                                    // Faturas em Aberto Refer�ncia 1
                                    "TOTAL",
                                    // Valor Fatura 1
                                    "",
                                    // Refer�ncia 2
                                    "",
                                    // Valor Fatura 2
                                    Util.formatarMoedaReal(totalFaturas),
                                    // Servi�os a Cobrar
                                    // C�digo
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    "",
                                    // Guias de Pagamento
                                    // N�mero
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    "",
                                    // Cr�ditos a Realizar
                                    // C�digo
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    ""
                            );
    
                            // adiciona o bean a cole��o
                            colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                        }
                    } else if (tipoAnterior instanceof DebitoACobrar) {
                        if (!(tipoAtual instanceof DebitoACobrar)) {
                            relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                    // Matr�cula do Im�vel
                                    idImovel,
                                    // Nome do Cliente
                                    nomeCliente,
                                    // Faturas em Aberto
                                    // Refer�ncia 1
                                    "",
                                    // Valor Fatura 1
                                    "",
                                    // Refer�ncia 2
                                    "",
                                    // Valor Fatura 2
                                    "",
                                    // Servi�os a Cobrar
                                    // C�digo
                                    "TOTAL",
                                    // Descri��o
                                    "",
                                    // Valor
                                    Util.formatarMoedaReal(totalServicos),
                                    // Guias de Pagamento
                                    // N�mero
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    "",
                                    // Cr�ditos a Realizar
                                    // C�digo
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    ""
                            );
    
                            // adiciona o bean a cole��o
                            colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                        }
                    } else if (tipoAnterior instanceof GuiaPagamento) {
                        if (!(tipoAtual instanceof GuiaPagamento)) {
                            relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                    // Matr�cula do Im�vel
                                    idImovel,
                                    // Nome do Cliente
                                    nomeCliente,
                                    // Faturas em Aberto
                                    // Refer�ncia 1
                                    "",
                                    // Valor Fatura 1
                                    "",
                                    // Refer�ncia 2
                                    "",
                                    // Valor Fatura 2
                                    "",
                                    // Servi�os a Cobrar
                                    // C�digo
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    "",
                                    // Guias de Pagamento
                                    // N�mero
                                    "TOTAL",
                                    // Descri��o
                                    "",
                                    // Valor
                                    Util.formatarMoedaReal(totalGuias),
                                    // Cr�ditos a Realizar
                                    // C�digo
                                    "",
                                    // Descri��o
                                    "",
                                    // Valor
                                    ""
                            );
    
                            // adiciona o bean a cole��o
                            colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                        }
                    }
    
                    if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {                     
                        Conta conta = parcelamentoItem.getContaGeral().getConta();
                        
                        // Verificamos o maior ano mes de ferencia para o tipo conta
                        if ( maiorAnoMesReferenciaDocumento == null || maiorAnoMesReferenciaDocumento < conta.getReferencia() ){
                            maiorAnoMesReferenciaDocumento = conta.getReferencia() ;
                        }
                        
                        // Verificamos o menor ano mes de ferencia para o tipo conta
                        if ( menorAnoMesReferenciaDocumento == null || menorAnoMesReferenciaDocumento > conta.getReferencia() ){
                            menorAnoMesReferenciaDocumento = conta.getReferencia() ;
                        }                               
                        
    
                        totalFaturas = totalFaturas.add(conta.getValorTotal());
    
                        if (colecaoRelatorioParcelamentoItensIterator.hasNext()) {
    
                            parcelamentoItem2 = (ParcelamentoItem) colecaoRelatorioParcelamentoItensIterator.next();
    
                            if (parcelamentoItem2.getContaGeral().getConta().getReferencia() != 0) {
    
                                Conta conta2 = parcelamentoItem2.getContaGeral().getConta();
                                
                                // Verificamos o maior ano mes de ferencia para o tipo conta
                                if ( maiorAnoMesReferenciaDocumento == null || maiorAnoMesReferenciaDocumento < conta2.getReferencia() ){
                                    maiorAnoMesReferenciaDocumento = conta2.getReferencia() ;
                                }
                                
                                // Verificamos o menor ano mes de ferencia para o tipo conta
                                if ( menorAnoMesReferenciaDocumento == null || menorAnoMesReferenciaDocumento > conta2.getReferencia() ){
                                    menorAnoMesReferenciaDocumento = conta2.getReferencia() ;
                                }                               
    
                                parcelamentoItem2 = null;
    
                                totalFaturas = totalFaturas.add(conta2.getValorTotal());
    
                                relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                        // Matr�cula do Im�vel
                                        idImovel,
                                        // Nome do Cliente
                                        nomeCliente,
                                        // Faturas em Aberto
                                        // Refer�ncia 1
                                        conta.getFormatarAnoMesParaMesAno(),
                                        // Valor Fatura 1
                                        Util.formatarMoedaReal(conta.getValorTotal()),
                                        // Refer�ncia 2
                                        conta2.getFormatarAnoMesParaMesAno(),
                                        // Valor Fatura 2
                                        Util.formatarMoedaReal(conta2.getValorTotal()),
                                        // Servi�os a Cobrar
                                        // C�digo
                                        "",
                                        // Descri��o
                                        "",
                                        // Valor
                                        "",
                                        // Guias de Pagamento
                                        // N�mero
                                        "",
                                        // Descri��o
                                        "",
                                        // Valor
                                        "",
                                        // Cr�ditos a Realizar
                                        // C�digo
                                        "",
                                        // Descri��o
                                        "",
                                        // Valor
                                        ""
                                );
    
                                // adiciona o bean a cole��o
                                colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
    
                            } else {
                                relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                        // Matr�cula do Im�vel
                                        idImovel,
                                        // Nome do Cliente
                                        nomeCliente,
                                        // Faturas em Aberto
                                        // Refer�ncia 1
                                        conta.getFormatarAnoMesParaMesAno(),
                                        // Valor Fatura 1
                                        Util.formatarMoedaReal(conta.getValorTotal()),
                                        // Refer�ncia 2
                                        "",
                                        // Valor Fatura 2
                                        "",
                                        // Servi�os a Cobrar
                                        // C�digo
                                        "",
                                        // Descri��o
                                        "",
                                        // Valor
                                        "",
                                        // Guias de Pagamento
                                        // N�mero
                                        "",
                                        // Descri��o
                                        "",
                                        // Valor
                                        "",
                                        // Cr�ditos a Realizar
                                        // C�digo
                                        "",
                                        // Descri��o
                                        "",
                                        // Valor
                                        ""
                                );
    
                                // adiciona o bean a cole��o
                                colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
    
                            }
                        } else {
                            relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                            // Matr�cula do Im�vel
                                            idImovel,
                                            // Nome do Cliente
                                            nomeCliente,
                                            // Faturas em Aberto
                                            // Refer�ncia 1
                                            conta.getFormatarAnoMesParaMesAno(),
                                            // Valor Fatura 1
                                            Util.formatarMoedaReal(conta.getValorTotal()),
                                            // Refer�ncia 2
                                            "",
                                            // Valor Fatura 2
                                            "",
                                            // Servi�os a Cobrar
                                            // C�digo
                                            "",
                                            // Descri��o
                                            "",
                                            // Valor
                                            "",
                                            // Guias de Pagamento
                                            // N�mero
                                            "",
                                            // Descri��o
                                            "",
                                            // Valor
                                            "",
                                            // Cr�ditos a Realizar
                                            // C�digo
                                            "",
                                            // Descri��o
                                            "",
                                            // Valor
                                            ""
                                    );
    
                                    // adiciona o bean a cole��o
                                    colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                        }
    
                    } else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
                        DebitoACobrar debitoACobrar = (DebitoACobrar) parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
                        totalServicos = totalServicos.add(debitoACobrar.getValorTotalComBonus());
    
                        relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                // Matr�cula do Im�vel
                                idImovel,
                                // Nome do Cliente
                                nomeCliente,
                                // Faturas em Aberto
                                // Refer�ncia 1
                                "",
                                // Valor Fatura 1
                                "",
                                // Refer�ncia 2
                                "",
                                // Valor Fatura 2
                                "",
                                // Servi�os a Cobrar
                                // C�digo
                                debitoACobrar.getDebitoTipo().getId().toString(),
                                // Descri��o
                                debitoACobrar.getDebitoTipo().getDescricao(),
                                // Valor
                                Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()),
                                // Guias de Pagamento
                                // N�mero
                                "",
                                // Descri��o
                                "",
                                // Valor
                                "",
                                // Cr�ditos a Realizar
                                // C�digo
                                "",
                                // Descri��o
                                "",
                                // Valor
                                ""
                        );
    
                        // adiciona o bean a cole��o
                        colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
    
                    } else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
                        GuiaPagamento guiaPagamento = (GuiaPagamento) parcelamentoItem
                                .getGuiaPagamentoGeral().getGuiaPagamento();
    
                        totalGuias = totalGuias.add(guiaPagamento.getValorDebito());
    
                        relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                // Matr�cula do Im�vel
                                idImovel,
                                // Nome do Cliente
                                nomeCliente,
                                // Faturas em Aberto
                                // Refer�ncia 1
                                "",
                                // Valor Fatura 1
                                "",
                                // Refer�ncia 2
                                "",
                                // Valor Fatura 2
                                "",
                                // Servi�os a Cobrar
                                // C�digo
                                "",
                                // Descri��o
                                "",
                                // Valor
                                "",
                                // Guias de Pagamento
                                // N�mero
                                guiaPagamento.getId().toString(),
                                // Descri��o
                                guiaPagamento.getDebitoTipo().getDescricao(),
                                // Valor
                                Util.formatarMoedaReal(guiaPagamento.getValorDebito()),
                                // Cr�ditos a Realizar
                                // C�digo
                                "",
                                // Descri��o
                                "",
                                // Valor
                                ""
                        );
    
                        // adiciona o bean a cole��o
                        colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                    } else if (parcelamentoItem.getCreditoARealizarGeral()
                            .getCreditoARealizar().getCreditoTipo().getId() != null) {
                        CreditoARealizar creditoARealizar = (CreditoARealizar) parcelamentoItem
                                .getCreditoARealizarGeral().getCreditoARealizar();
    
                            totalCreditos = totalCreditos.add(creditoARealizar.getValorTotalComBonus());
    
                        relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                                // Matr�cula do Im�vel
                                idImovel,
                                // Nome do Cliente
                                nomeCliente,
                                // Faturas em Aberto
                                // Refer�ncia 1
                                "",
                                // Valor Fatura 1
                                "",
                                // Refer�ncia 2
                                "",
                                // Valor Fatura 2
                                "",
                                // Servi�os a Cobrar
                                // C�digo
                                "",
                                // Descri��o
                                "",
                                // Valor
                                "",
                                // Guias de Pagamento
                                // N�mero
                                "",
                                // Descri��o
                                "",
                                // Valor
                                "",
                                // Cr�ditos a Realizar
                                // C�digo
                                creditoARealizar.getCreditoTipo().getId().toString(),
                                // Descri��o
                                creditoARealizar.getCreditoTipo().getDescricao(),
                                // Valor
                                Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus())
                        );
    
                        // adiciona o bean a cole��o
                        colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
    
                    }
    
                }
    
                if (tipoAtual instanceof Conta) {
                    relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                            // Matr�cula do Im�vel
                            idImovel,
                            // Nome do Cliente
                            nomeCliente,
                            // Faturas em Aberto
                            // Refer�ncia 1
                            "TOTAL",
                            // Valor Fatura 1
                            "",
                            // Refer�ncia 2
                            "",
                            // Valor Fatura 2
                            Util.formatarMoedaReal(totalFaturas),
                            // Servi�os a Cobrar
                            // C�digo
                            "",
                            // Descri��o
                            "",
                            // Valor
                            "",
                            // Guias de Pagamento
                            // N�mero
                            "",
                            // Descri��o
                            "",
                            // Valor
                            "",
                            // Cr�ditos a Realizar
                            // C�digo
                            "",
                            // Descri��o
                            "",
                            // Valor
                            ""
                    );
    
                    // adiciona o bean a cole��o
                    colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                } else if (tipoAtual instanceof DebitoACobrar) {
                    relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                            // Matr�cula do Im�vel
                            idImovel,
                            // Nome do Cliente
                            nomeCliente,
                            // Faturas em Aberto
                            // Refer�ncia 1
                            "",
                            // Valor Fatura 1
                            "",
                            // Refer�ncia 2
                            "",
                            // Valor Fatura 2
                            "",
                            // Servi�os a Cobrar
                            // C�digo
                            "TOTAL",
                            // Descri��o
                            "",
                            // Valor
                            Util.formatarMoedaReal(totalServicos),
                            // Guias de Pagamento
                            // N�mero
                            "",
                            // Descri��o
                            "",
                            // Valor
                            "",
                            // Cr�ditos a Realizar
                            // C�digo
                            "",
                            // Descri��o
                            "",
                            // Valor
                            ""
                    );
    
                    // adiciona o bean a cole��o
                    colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                    
                } else if (tipoAtual instanceof GuiaPagamento) {
                    relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                            // Matr�cula do Im�vel
                            idImovel,
                            // Nome do Cliente
                            nomeCliente,
                            // Faturas em Aberto
                            // Refer�ncia 1
                            "",
                            // Valor Fatura 1
                            "",
                            // Refer�ncia 2
                            "",
                            // Valor Fatura 2
                            "",
                            // Servi�os a Cobrar
                            // C�digo
                            "",
                            // Descri��o
                            "",
                            // Valor
                            "",
                            // Guias de Pagamento
                            // N�mero
                            "TOTAL",
                            // Descri��o
                            "",
                            // Valor
                            Util.formatarMoedaReal(totalGuias),
                            // Cr�ditos a Realizar
                            // C�digo
                            "",
                            // Descri��o
                            "",
                            // Valor
                            ""
                    );
    
                    // adiciona o bean a cole��o
                    colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                    
                } else if (tipoAtual instanceof CreditoARealizar) {
    
                    relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(
    
                            // Matr�cula do Im�vel
                            idImovel,
                            // Nome do Cliente
                            nomeCliente,
                            // Faturas em Aberto
                            // Refer�ncia 1
                            "",
                            // Valor Fatura 1
                            "",
                            // Refer�ncia 2
                            "",
                            // Valor Fatura 2
                            "",
                            // Servi�os a Cobrar
                            // C�digo
                            "",
                            // Descri��o
                            "",
                            // Valor
                            "",
                            // Guias de Pagamento
                            // N�mero
                            "",
                            // Descri��o
                            "",
                            // Valor
                            "",
                            // Cr�ditos a Realizar
                            // C�digo
                            "TOTAL",
                            // Descri��o
                            "",
                            // Valor
                            Util.formatarMoedaReal(totalCreditos)
                    );
    
                    // adiciona o bean a cole��o
                    colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
                }
    
            }

			
			// Verificamos se a empresa informada � a CAER, pois a montagem
			// do relatorio � diferente
			if( sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER ) ){
				
				String rgCliente = "";
				String rgClienteResponsavel = "";
				
				if (parcelamentoRelatorioHelper.getRgClienteParcelamento()!= null
						&& !parcelamentoRelatorioHelper.getRgClienteParcelamento().equals("")){
					
					rgClienteResponsavel = parcelamentoRelatorioHelper.getRgClienteParcelamento();
					
				}
					if(parcelamentoRelatorioHelper.getRgCliente() != null){
					if(parcelamentoRelatorioHelper.getDescOrgaoExpRgCliente() != null && 
							parcelamentoRelatorioHelper.getSiglaUnidadeFederacaoRgCliente() != null){
						rgCliente = parcelamentoRelatorioHelper.getRgCliente() + "-" + 
						  parcelamentoRelatorioHelper.getDescOrgaoExpRgCliente().trim() + "/" + 
						  parcelamentoRelatorioHelper.getSiglaUnidadeFederacaoRgCliente();
					  
					}else{
						rgCliente = parcelamentoRelatorioHelper.getRgCliente();	
					}
				}				
				
                if(idFuncionario.equals("") && usuarioLogado.getFuncionario() != null){
                    idFuncionario = usuarioLogado.getFuncionario().getId().toString();
                }
                
                
                
                
				relatorioParcelamentoBean = new RelatorioParcelamentoBean(
						// Matr�cula do Im�vel
						parcelamentoRelatorioHelper.getIdImovel().toString(),
						// Nome Cliente
						nomeCliente,
						// Endere�o
						parcelamentoRelatorioHelper.getEndereco(),
						// CPF/CNPJ
						cpfCnpjCliente,
						// Telefone
						parcelamentoRelatorioHelper.getTelefone(),
						// Data Parcelamento
						Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
						// D�bitos
						// Faturas em Aberto
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
						// Servi�os a Cobrar
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
						// Atualiza��o Monet�ria
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
						// Juros/Mora
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
						// Multa
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
						// Guia Pagamento
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
						// Parcelamento a Cobrar
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
						// Total D�bitos
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
						// Descontos
						// Desconto de Acr�scimos
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
						// Descontos de Antiguidade
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
						// Desconto de Inatividade
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
						// Cr�ditos a Realizar
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
						// Total Descontos
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
						// Valor a Ser Negociado
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociado().subtract(parcelamentoRelatorioHelper.getValorTotalDescontosSemValorCreditos())),
						// Condi��es de Negocia��o
						// Valor da Entrada
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
						// N�mero de Parcelas
						parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
						// Valor da Parcela
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
						// Valor a Ser Parcelado
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
						// Solicita��o de Restabelecimento
						parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
						// Munic�pio e Data
						parcelamentoRelatorioHelper.getNomeMunicipio() + ", " + dataCorrente,
						// Id do Parcelamento
						idParcelamento.toString(),
						// Unidade do Usu�rio
						descricaoUnidadeUsuario,
						// Id do Funcion�rio
						idFuncionario,
						// Nome do Cliente do Parcelamento
						parcelamentoRelatorioHelper.getNomeClienteParcelamento(),
						// Cpf do Cliente do Parcelamento
						parcelamentoRelatorioHelper.getCpfClienteParcelamento(),
						// P�gina
						"2",
						// Cole��o de Detalhamentos
						colecaoRelatorioParcelamentoDetalhamentosBeans,
						//codigo da empresa
						sistemaParametro.getCodigoEmpresaFebraban().toString(),
						//Rg
						rgCliente,
					    //nome usuario
                        usuarioLogado.getNomeUsuario(),
						//matricula usuario
                        usuarioLogado.getLogin(),
						parcelamentoRelatorioHelper.getNomeMunicipio(),
						parcelamentoRelatorioHelper.getMesAnoInicioParcelamento(),
						parcelamentoRelatorioHelper.getMesAnoFinalParcelamento(),
						parcelamentoRelatorioHelper.getTaxaJuros(),
						//Desconto de San��es Regulamentares
						Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
						//Desconto Tarifa Social
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
	                    // CPF do usu�rio logado
	                    Util.formatarCpf( usuarioLogado.getCpf() ),
	                    parcelamentoRelatorioHelper.getNomeDiretorComercial(),
	                    parcelamentoRelatorioHelper.getCpfDiretorComercial(),
	                    parcelamentoRelatorioHelper.getProfissao(),
	                    parcelamentoRelatorioHelper.getNomeDevedor(),
	                    parcelamentoRelatorioHelper.getCnpjDevedor(),
	                    parcelamentoRelatorioHelper.getEnderecoDevedor(),
	                    parcelamentoRelatorioHelper.getTelefoneDevedor(),
	                    parcelamentoRelatorioHelper.getIndicadorPessoaJuridica(),
	                    maiorAnoMesReferenciaDocumento,
	                    menorAnoMesReferenciaDocumento,
                        "",
                        parcelamentoRelatorioHelper.getBairro() +"",
                        parcelamentoRelatorioHelper.getCodigoRota() +"",
                        rgClienteResponsavel,
                        parcelamentoRelatorioHelper.getEnderecoResponsavel(),
                        parcelamentoRelatorioHelper.getBairroImovelResponsavel()
	                    );				
				
				
				// Nome
				if (parcelamentoRelatorioHelper.getNomeClienteParcelamento() != null) {
					relatorioParcelamentoBean.setNomeClienteSuperior(parcelamentoRelatorioHelper.getNomeClienteParcelamento());
				} else {
					relatorioParcelamentoBean.setNomeClienteSuperior("");
				}

				// CPF/CNPJ
				if (parcelamentoRelatorioHelper.getCpfClienteParcelamento() != null) {
					relatorioParcelamentoBean.setCpfCnpjClienteSuperior(parcelamentoRelatorioHelper.getCpfClienteParcelamento());
				} else {
					relatorioParcelamentoBean.setCpfCnpjClienteSuperior("");
				}

				if (parcelamentoRelatorioHelper.getIdClienteParcelamento() != null) {
					// Telefone
					Collection colecaoClienteFone = fachada.pesquisarClienteFone(parcelamentoRelatorioHelper.getIdClienteParcelamento());

					ClienteFone clienteFone = null;

					if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {

						Iterator colecaoClienteFoneIterator = colecaoClienteFone.iterator();

						while (colecaoClienteFoneIterator.hasNext()) {
							clienteFone = (ClienteFone) colecaoClienteFoneIterator.next();

							if (clienteFone.getIndicadorTelefonePadrao() != null
									&& clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
								break;
							}
						}

						relatorioParcelamentoBean.setTelefoneClienteSuperior(clienteFone.getDddTelefone());

					} else {
						relatorioParcelamentoBean.setTelefoneClienteSuperior("");
					}
				}	
				
				// adiciona o bean a cole��o
				relatorioParcelamentosBeans.add(relatorioParcelamentoBean);				
			}else if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
             
                String rgCliente = "";
                
                if (parcelamentoRelatorioHelper.getRgClienteParcelamento()!= null
                      && !parcelamentoRelatorioHelper.getRgClienteParcelamento().equals("")){
              
                    rgCliente = parcelamentoRelatorioHelper.getRgClienteParcelamento();
                  
                }else if(parcelamentoRelatorioHelper.getRgCliente() != null){
                    if(parcelamentoRelatorioHelper.getDescOrgaoExpRgCliente() != null && 
                          parcelamentoRelatorioHelper.getSiglaUnidadeFederacaoRgCliente() != null){
                        rgCliente = parcelamentoRelatorioHelper.getRgCliente() + "-" + 
                        parcelamentoRelatorioHelper.getDescOrgaoExpRgCliente().trim() + "/" + 
                        parcelamentoRelatorioHelper.getSiglaUnidadeFederacaoRgCliente();
                    
                    }else{
                        rgCliente = parcelamentoRelatorioHelper.getRgCliente(); 
                    }
                }
              
                if(idFuncionario.equals("") && usuarioLogado.getFuncionario() != null){
                    idFuncionario = usuarioLogado.getFuncionario().getId().toString();
                }
                
                String mostrarValoresDescontos;
                if(!Util.formatarBigDecimalParaString(parcelamentoRelatorioHelper.getValorDescontoMulta()).equals("000") || 
                		!Util.formatarBigDecimalParaString(parcelamentoRelatorioHelper.getValorDescontoJuros()).equals("000") || 
                		!Util.formatarBigDecimalParaString(parcelamentoRelatorioHelper.getValorDescontoAtualizacaoMonetaria()).equals("000")){
                	mostrarValoresDescontos = "";
                }else{
                	mostrarValoresDescontos = null;
                }
                
                //calcular valor negociado - Vivianne Sousa - 16/08/2012 - Alexandre Cabral
                BigDecimal valorNegociado = parcelamentoRelatorioHelper.getValorASerNegociado();
                if(parcelamentoRelatorioHelper.getValorTotalDescontos() != null){
                	valorNegociado = valorNegociado.subtract(parcelamentoRelatorioHelper.getValorTotalDescontos());
                }
                
                relatorioParcelamentoBean = new RelatorioParcelamentoBean(
    
                        // Matr�cula do Im�vel
                        parcelamentoRelatorioHelper.getIdImovel().toString(),
                        // Nome Cliente
                        nomeCliente,
                        // Endere�o
                        parcelamentoRelatorioHelper.getEndereco(),
                        // CPF/CNPJ
                        cpfCnpjCliente,
                        // Telefone
                        parcelamentoRelatorioHelper.getTelefone(),
                        // Data Parcelamento
                        Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
                        // D�bitos
                        // Faturas em Aberto
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
                        // Servi�os a Cobrar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
                        // Atualiza��o Monet�ria
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
                        // Juros/Mora
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
                        // Multa
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
                        // Guia Pagamento
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
                        // Parcelamento a Cobrar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
                        // Total D�bitos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
                        // Descontos
                        // Desconto de Acr�scimos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
                        // Descontos de Antiguidade
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
                        // Desconto de Inatividade
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
                        // Cr�ditos a Realizar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
                        // Total Descontos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
                        // Valor a Ser Negociado
                        Util.formatarMoedaReal(valorNegociado),
                        // Condi��es de Negocia��o
                        // Valor da Entrada
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
                        // N�mero de Parcelas
                        parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
                        // Valor da Parcela
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
                        // Valor a Ser Parcelado
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
                        // Solicita��o de Restabelecimento
                        parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
                        // Munic�pio e Data
                        parcelamentoRelatorioHelper.getNomeMunicipio() + ", " + dataCorrente,
                        // Id do Parcelamento
                        idParcelamento.toString(),
                        // Unidade do Usu�rio
                        descricaoUnidadeUsuario,
                        // Id do Funcion�rio
                        idFuncionario,
                        // Nome do Cliente do Parcelamento
                        parcelamentoRelatorioHelper.getNomeClienteParcelamento(),
                        // Cpf do Cliente do Parcelamento
                        parcelamentoRelatorioHelper.getCpfClienteParcelamento(),
                        // P�gina
                        "1",
                        // Cole��o de Detalhamentos
                        colecaoRelatorioParcelamentoDetalhamentosBeans,
                        //codigo da empresa
                        sistemaParametro.getCodigoEmpresaFebraban().toString(),
                        //rgCliente
                        rgCliente,
                        //nome usuario
                        usuarioLogado.getNomeUsuario(),
                        //matricula usuario
                        usuarioLogado.getLogin(),
                        //municipio
                        parcelamentoRelatorioHelper.getNomeMunicipio(),
                        //inicio parcelamento
                        parcelamentoRelatorioHelper.getMesAnoInicioParcelamento(),
                        //final parcelamento
                        parcelamentoRelatorioHelper.getMesAnoFinalParcelamento(),
                        parcelamentoRelatorioHelper.getTaxaJuros(),
                        //Desconto de San��es Regulamentares
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
                        //Desconto Tarifa Social
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
                        // CPF do usu�rio logado
                        usuarioLogado.getCpf(),
                        parcelamentoRelatorioHelper.getNomeDiretorComercial(),
                        parcelamentoRelatorioHelper.getCpfDiretorComercial(),
                        parcelamentoRelatorioHelper.getProfissao(),
                        parcelamentoRelatorioHelper.getNomeDevedor(),
                        parcelamentoRelatorioHelper.getCnpjDevedor(),
                        parcelamentoRelatorioHelper.getEnderecoDevedor(),
                        parcelamentoRelatorioHelper.getTelefoneDevedor(),
                        parcelamentoRelatorioHelper.getIndicadorPessoaJuridica(),
                        maiorAnoMesReferenciaDocumento,
                        menorAnoMesReferenciaDocumento,
                        "",
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoDebitoTotal()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTipoDebito()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoMulta()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoJuros()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAtualizacaoMonetaria()),
                        mostrarValoresDescontos,
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoEntradaParc()));
                
                //adiciona o bean a cole��o
                relatorioParcelamentosBeans.add(relatorioParcelamentoBean); 
                
			}else if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)){
                
				String nomeTestemunha1 = "";
	     		String cpfTestemunha1 = "";
	     		String nomeTestemunha2 = "";
	     		String cpfTestemunha2 = "";
	     		
	     		
	     		//Comentado por S�vio Luiz Dia:17/12/2008.
	     		// A pedido de Manoel Paulo.
//	     		Collection colecaoTestemunha = fachada.obterNomeCPFTestemunhas(unidadeUsuario.getId());
//	     		if (colecaoTestemunha != null && !colecaoTestemunha.isEmpty()){
//	     			Iterator iterTestemunha = colecaoTestemunha.iterator();
//	     			
//	     			Usuario usuario1 = (Usuario)iterTestemunha.next();
//	     			nomeTestemunha1 = usuario1.getNomeUsuario();
//		     		cpfTestemunha1 = Util.formatarCpf(usuario1.getCpf());
//	     			
//	     			if (iterTestemunha.hasNext()){
//	     				Usuario usuario2 = (Usuario)iterTestemunha.next();
//	     				nomeTestemunha2 = usuario2.getNomeUsuario();
//			     		cpfTestemunha2 = Util.formatarCpf(usuario2.getCpf());
//	     			}
//		     		
//	     		}
				
				String textoClausula2 = "";
				String paragrafoSegundo = "";
				relatorioParcelamentoBean = new RelatorioParcelamentoBean(
						 // Matr�cula do Im�vel
                        parcelamentoRelatorioHelper.getIdImovel().toString(),
                        // Nome Cliente
                        nomeCliente,
                        // Endere�o
                        parcelamentoRelatorioHelper.getEndereco(),
                        // CPF/CNPJ
                        cpfCnpjCliente,
                        // Telefone
                        parcelamentoRelatorioHelper.getTelefone(),
                        // Data Parcelamento
                        Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
                        // D�bitos
                        // Faturas em Aberto
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
                        // Servi�os a Cobrar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
                        // Atualiza��o Monet�ria
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
                        // Juros/Mora
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
                        // Multa
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
                        // Guia Pagamento
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
                        // Parcelamento a Cobrar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
                        // Total D�bitos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
                        // Descontos
                        // Desconto de Acr�scimos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
                        // Descontos de Antiguidade
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
                        // Desconto de Inatividade
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
                        // Cr�ditos a Realizar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
                        // Total Descontos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
                        // Valor a Ser Negociado
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociado().subtract(parcelamentoRelatorioHelper.getValorTotalDescontosSemValorCreditos())),
                        // Condi��es de Negocia��o
                        // Valor da Entrada
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
                        // N�mero de Parcelas
                        parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
                        // Valor da Parcela
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
                        // Valor a Ser Parcelado
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
                        // Solicita��o de Restabelecimento
                        parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
                        // Munic�pio e Data
                        parcelamentoRelatorioHelper.getNomeMunicipio() + ", " + dataCorrente,
                        // Id do Parcelamento
                        idParcelamento.toString(),
                        "1",
                        //Cole��o de Detalhamentos
                        colecaoRelatorioParcelamentoDetalhamentosBeans,
                        //codigo da empresa
                        sistemaParametro.getCodigoEmpresaFebraban().toString(),
                        //valor dos juros
                        parcelamentoRelatorioHelper.getTaxaJuros(),
                        //Desconto de San��es Regulamentares
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
                        //Desconto Tarifa Social
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
                        //Valor A Ser Negociado Sem Desconto
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociadoSemDesconto()) + 
                        "(" + Util.valorExtenso(parcelamentoRelatorioHelper.getValorASerNegociadoSemDesconto()) + ")",
                        //Valor Total Descontos Sem Valor Creditos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontosSemValorCreditos())+ 
                        "(" + Util.valorExtenso(parcelamentoRelatorioHelper.getValorTotalDescontosSemValorCreditos()) + ")",
                        "(" + Util.valorExtenso(parcelamentoRelatorioHelper.getValorASerNegociado().subtract(parcelamentoRelatorioHelper.getValorTotalDescontosSemValorCreditos())) + ")",
                        "(" + Util.valorExtenso(parcelamentoRelatorioHelper.getValorEntrada()) + ")",
                        "(" + Util.valorExtenso(parcelamentoRelatorioHelper.getValorParcela()) + ")",
                        Util.completaString(nomeTestemunha1,47),
                        Util.completaString(cpfTestemunha1,47),
                        nomeTestemunha2,
                        cpfTestemunha2,
                        parcelamentoRelatorioHelper.getTextoClausula2(),
                        parcelamentoRelatorioHelper.getParagrafoSegundo()                    
						);
				
				
				// Nome
				if (parcelamentoRelatorioHelper.getNomeClienteParcelamento() != null) {
					relatorioParcelamentoBean.setNomeClienteSuperior(parcelamentoRelatorioHelper.getNomeClienteParcelamento());
				} else {
					relatorioParcelamentoBean.setNomeClienteSuperior("");
				}

				// CPF/CNPJ
				if (parcelamentoRelatorioHelper.getCpfClienteParcelamento() != null) {
					relatorioParcelamentoBean.setCpfCnpjClienteSuperior(parcelamentoRelatorioHelper.getCpfClienteParcelamento());
				} else {
					relatorioParcelamentoBean.setCpfCnpjClienteSuperior("");
				}

				if (parcelamentoRelatorioHelper.getIdClienteParcelamento() != null) {
					// Telefone
					Collection colecaoClienteFone = fachada.pesquisarClienteFone(parcelamentoRelatorioHelper.getIdClienteParcelamento());

					ClienteFone clienteFone = null;

					if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {

						Iterator colecaoClienteFoneIterator = colecaoClienteFone.iterator();

						while (colecaoClienteFoneIterator.hasNext()) {
							clienteFone = (ClienteFone) colecaoClienteFoneIterator.next();

							if (clienteFone.getIndicadorTelefonePadrao() != null
									&& clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
								break;
							}
						}

						relatorioParcelamentoBean.setTelefoneClienteSuperior(clienteFone.getTelefone());

					} else {
						relatorioParcelamentoBean.setTelefoneClienteSuperior("");
					}
				}	
				
		        //adiciona o bean a cole��o
                relatorioParcelamentosBeans.add(relatorioParcelamentoBean);    
			} else if(sistemaParametro.getCodigoEmpresaFebraban().equals(
									SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {
				
				String rgCliente = " _________________ ";
				
				if (parcelamentoRelatorioHelper.getRgClienteParcelamento()!= null
						&& !parcelamentoRelatorioHelper.getRgClienteParcelamento().equals("")){
					
					rgCliente = parcelamentoRelatorioHelper.getRgClienteParcelamento();
					
				}else if(parcelamentoRelatorioHelper.getRgCliente() != null){
					if(parcelamentoRelatorioHelper.getDescOrgaoExpRgCliente() != null && 
							parcelamentoRelatorioHelper.getSiglaUnidadeFederacaoRgCliente() != null){
						rgCliente = parcelamentoRelatorioHelper.getRgCliente() + "-" + 
						  parcelamentoRelatorioHelper.getDescOrgaoExpRgCliente().trim() + "/" + 
						  parcelamentoRelatorioHelper.getSiglaUnidadeFederacaoRgCliente();
					  
					}else{
						rgCliente = parcelamentoRelatorioHelper.getRgCliente();	
					}
				}		
				
				String valorAserNegociadoPorExtenso = Util.valorExtenso(parcelamentoRelatorioHelper.getValorASerNegociado());
				String valorDeEntradaProExtenso = Util.valorExtenso(parcelamentoRelatorioHelper.getValorEntrada());
				String numeroDeParcelasPorExtenso = Util.valorExtenso(new BigDecimal(parcelamentoRelatorioHelper.getNumeroParcelas()));
				if ( numeroDeParcelasPorExtenso != null && !numeroDeParcelasPorExtenso.equals("ZERO") ) {
					numeroDeParcelasPorExtenso = Util.removerUltimosCaracteres(numeroDeParcelasPorExtenso, 5);
				}
				
				String valorDaParcelaPorExtenso = Util.valorExtenso(parcelamentoRelatorioHelper.getValorParcela());
				String valorTOTALDasParcelasPorExtenso = Util.valorExtenso(parcelamentoRelatorioHelper.getValorASerParcelado());
				String valorDescontoPorExtenso = Util.valorExtenso(parcelamentoRelatorioHelper.getValorTotalDescontos());
				
				//Consultar Data de Vencimento da Guia de Pagamento, 
				//para informar a data de vencimento do Valor de Entrada a ser pago
				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
						FiltroGuiaPagamento.PARCELAMENTO_ID, idParcelamento ));
				Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
				GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
				
				String dataVencimentoGuiaPagamento = "";
				
				if (guiaPagamento != null && !guiaPagamento.equals("") ) {

					if ( guiaPagamento.getDataVencimento() != null &&  !guiaPagamento.getDataVencimento().equals("") ) {
						dataVencimentoGuiaPagamento = "" + Util.formatarData(guiaPagamento.getDataVencimento());
					}
					
				}
				
				String valorDesconto = Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos());
				String descontoNegociado = "";
				if (valorDesconto != null && !valorDesconto.equals("0,00")){
					descontoNegociado = "relatorioDeveApresentarParagrafo2";
					
				}
				String nomeTestemunha1 = "";
				String nomeTestemunha2 = "";
				String cpfTestemunha1 = "";
				String cpfTestemunha2 = "";
	     		
				Collection colecaoTestemunha = fachada.obterNomeCPFTestemunhas(unidadeUsuario.getId());
	     		if (colecaoTestemunha != null && !colecaoTestemunha.isEmpty()){
	     			Iterator iterTestemunha = colecaoTestemunha.iterator();
	     			
	     			Usuario usuario1 = (Usuario)iterTestemunha.next();
	     			nomeTestemunha1 = usuario1.getNomeUsuario();
		     		cpfTestemunha1 = Util.formatarCpf(usuario1.getCpf());
	     			
	     			if (iterTestemunha.hasNext()){
	     				Usuario usuario2 = (Usuario)iterTestemunha.next();
	     				nomeTestemunha2 = usuario2.getNomeUsuario();
			     		cpfTestemunha2 = Util.formatarCpf(usuario2.getCpf());
	     			}
		     		
	     		}
	     		
	     		String localidadeDescricao ="";
	     		if (parcelamentoRelatorioHelper.getLocalidade() != null && 
	     				!parcelamentoRelatorioHelper.getLocalidade().equals("")) {
	     			localidadeDescricao = parcelamentoRelatorioHelper.getLocalidade();
	     		}
	     		
	     		String setorComercialDescricao = "";
	     		if (parcelamentoRelatorioHelper.getSetorComercial() != null && 
	     				!parcelamentoRelatorioHelper.getSetorComercial().equals("")) {
	     			setorComercialDescricao = parcelamentoRelatorioHelper.getSetorComercial();
	     		}
	     		
	     		String ddd = "";
	     		String telefone = "";
	     		if (parcelamentoRelatorioHelper.getTelefoneDevedor() != null &&
	     				!parcelamentoRelatorioHelper.getTelefoneDevedor().equals("") ) {
	     			ddd = parcelamentoRelatorioHelper.getTelefoneDevedor().substring(1,3);
	     			telefone = parcelamentoRelatorioHelper.getTelefoneDevedor().substring(4, 12 );
	     		}
	     		//Verificar se o Usuario � funcionario para informar seu cargo ou n�o
	     		String nomeFuncinario = "";
				String cargoFuncionario = " ATENDENTE ";
	     		if (parcelamentoRelatorioHelper.getNomeUsuarioParcelamento() != null && 
	     				!parcelamentoRelatorioHelper.getNomeUsuarioParcelamento().equals("")){
	     		
	     			FiltroFuncionario filtroFuncionarioUsuario = new FiltroFuncionario();
	     			filtroFuncionarioUsuario.adicionarParametro(new ParametroSimples(FiltroFuncionario.NOME, 
	     					parcelamentoRelatorioHelper.getNomeUsuarioParcelamento()));
	     			filtroFuncionarioUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionarioCargo");
	     			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionarioUsuario, Funcionario.class.getName());
	     			Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
	     			if (funcionario != null && !funcionario.equals("")) {
	     				nomeFuncinario = funcionario.getNome();
	     				cargoFuncionario = ""+ funcionario.getFuncionarioCargo().getDescricao();
	     			} else{
	     				nomeFuncinario = parcelamentoRelatorioHelper.getNomeUsuarioParcelamento();
	     			}
	     		}
	     	
	     		Calendar calendar = Calendar.getInstance();
	     		//em java janeiro � 0(ZERO)
	     		int mes  = calendar.get(Calendar.MONTH) + 1;
	     		String dia = ""+ calendar.get(Calendar.DAY_OF_MONTH);
	     		String ano = ""+ calendar.get(Calendar.YEAR);
	     		
	     		String mesExtenso = Util.retornaDescricaoMes(mes);
	     		
				 relatorioParcelamentoBean = new RelatorioParcelamentoBean(
	                        
						 	// Matr�cula do Im�vel
	                        parcelamentoRelatorioHelper.getIdImovel().toString(),
	                        // Nome Cliente
	                        nomeCliente,
	                        // Endere�o do Imovel
	                        parcelamentoRelatorioHelper.getEndereco(),
	                        // CPF/CNPJ
	                        cpfCnpjCliente,
	                        // Telefone
	                        telefone,
	                        // Data Parcelamento
	                        Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
	                        // D�bitos
	                        // Faturas em Aberto
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
	                        // Servi�os a Cobrar
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
	                        // Atualiza��o Monet�ria
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
	                        // Juros/Mora
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
	                        // Multa
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
	                        // Guia Pagamento
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
	                        // Parcelamento a Cobrar
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
	                        // Total D�bitos
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
	                        // Descontos
	                        // Desconto de Acr�scimos
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
	                        // Descontos de Antiguidade
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
	                        // Desconto de Inatividade
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
	                        // Cr�ditos a Realizar
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
	                        // Total Descontos
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
	                        // Valor a Ser Negociado
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociado()),
	                        // Condi��es de Negocia��o
	                        // Valor da Entrada
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
	                        // N�mero de Parcelas
	                        parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
	                        // Valor da Parcela
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
	                        // Valor a Ser Parcelado
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
	                        // Solicita��o de Restabelecimento
	                        parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
	                        // Munic�pio e Data
	                        "",
	                        // Id do Parcelamento
	                        idParcelamento.toString(),
	                        // Unidade do Usu�rio
	                        descricaoUnidadeUsuario,
	                        // Id do Funcion�rio
	                        idFuncionario,
	                        // Nome do Cliente do Parcelamento
	                        parcelamentoRelatorioHelper.getNomeClienteParcelamento(),
	                        // Cpf do Cliente do Parcelamento
	                        parcelamentoRelatorioHelper.getCpfClienteParcelamento(),
	                        // P�gina
	                        "1",
	                        // Cole��o de Detalhamentos
	                        colecaoRelatorioParcelamentoDetalhamentosBeans,
	                        //codigo da empresa
	                        sistemaParametro.getCodigoEmpresaFebraban().toString(),
	                        //rgCliente
	                        rgCliente,
	                        //nome usuario
	                        "",
	                        //matricula usuario
	                        "",
	                        //municipio
	                        "",
	                        //inicio parcelamento
	                        "",
	                        //final parcelamento
	                        "",
	                        parcelamentoRelatorioHelper.getTaxaJuros(),
	                        //Desconto de San��es Regulamentares
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
	                        //Desconto Tarifa Social
	                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
	                        // CPF do usu�rio logado
	                        usuarioLogado.getCpf(),
	                        parcelamentoRelatorioHelper.getNomeDiretorComercial(),
	                        parcelamentoRelatorioHelper.getCpfDiretorComercial(),
	                        parcelamentoRelatorioHelper.getProfissao(),
	                        parcelamentoRelatorioHelper.getNomeDevedor(),
	                        parcelamentoRelatorioHelper.getCnpjDevedor(),
	                        parcelamentoRelatorioHelper.getEnderecoDevedor(),
	                        parcelamentoRelatorioHelper.getTelefoneDevedor(),
	                        parcelamentoRelatorioHelper.getIndicadorPessoaJuridica(),
	                        //nome do usuario que efetuou o parcelamento 
	                        parcelamentoRelatorioHelper.getNomeUsuarioParcelamento(),
	                        nomeFuncinario,
	                        cargoFuncionario,
	                        maiorAnoMesReferenciaDocumento,
	                        menorAnoMesReferenciaDocumento,
	                        valorAserNegociadoPorExtenso,
	                        valorDeEntradaProExtenso,
	                        dataVencimentoGuiaPagamento,
	                        numeroDeParcelasPorExtenso,
	                        valorDaParcelaPorExtenso,
	                        valorTOTALDasParcelasPorExtenso, 
	                        descontoNegociado,
	                        valorDescontoPorExtenso,
	                        ""+ parcelamentoRelatorioHelper.getCodigoRota(),
	                        nomeTestemunha1,
	                        cpfTestemunha1,
	                        nomeTestemunha2,
	                        cpfTestemunha2,
	                        localidadeDescricao,
	                        setorComercialDescricao,
	                        ddd,
	                        mesExtenso,
	                        dia,
	                        ano);
				 
				 	
				 	//adiciona o bean a cole��o
	                relatorioParcelamentosBeans.add(relatorioParcelamentoBean);   
				
            }else{
                relatorioParcelamentoBean = new RelatorioParcelamentoBean(
                        
                        // Matr�cula do Im�vel
                        parcelamentoRelatorioHelper.getIdImovel().toString(),
                        // Nome Cliente
                        nomeCliente,
                        // Endere�o
                        parcelamentoRelatorioHelper.getEndereco(),
                        // CPF/CNPJ
                        cpfCnpjCliente,
                        // Telefone
                        parcelamentoRelatorioHelper.getTelefone(),
                        // Data Parcelamento
                        Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
                        // D�bitos
                        // Faturas em Aberto
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
                        // Servi�os a Cobrar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
                        // Atualiza��o Monet�ria
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
                        // Juros/Mora
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
                        // Multa
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
                        // Guia Pagamento
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
                        // Parcelamento a Cobrar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
                        // Total D�bitos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
                        // Descontos
                        // Desconto de Acr�scimos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
                        // Descontos de Antiguidade
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
                        // Desconto de Inatividade
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
                        // Cr�ditos a Realizar
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
                        // Total Descontos
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
                        // Valor a Ser Negociado
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociado()),
                        // Condi��es de Negocia��o
                        // Valor da Entrada
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
                        // N�mero de Parcelas
                        parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
                        // Valor da Parcela
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
                        // Valor a Ser Parcelado
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
                        // Solicita��o de Restabelecimento
                        parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
                        // Munic�pio e Data
                        "",
                        // Id do Parcelamento
                        idParcelamento.toString(),
                        // Unidade do Usu�rio
                        descricaoUnidadeUsuario,
                        // Id do Funcion�rio
                        idFuncionario,
                        // Nome do Cliente do Parcelamento
                        parcelamentoRelatorioHelper.getNomeClienteParcelamento(),
                        // Cpf do Cliente do Parcelamento
                        parcelamentoRelatorioHelper.getCpfClienteParcelamento(),
                        // P�gina
                        "1",
                        // Cole��o de Detalhamentos
                        colecaoRelatorioParcelamentoDetalhamentosBeans,
                        //codigo da empresa
                        sistemaParametro.getCodigoEmpresaFebraban().toString(),
                        //rgCliente
                        "",
                        //nome usuario
                        "",
                        //matricula usuario
                        "",
                        //municipio
                        "",
                        //inicio parcelamento
                        "",
                        //final parcelamento
                        "",
                        parcelamentoRelatorioHelper.getTaxaJuros(),
                        //Desconto de San��es Regulamentares
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
                        //Desconto Tarifa Social
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
                        // CPF do usu�rio logado
                        usuarioLogado.getCpf(),
                        parcelamentoRelatorioHelper.getNomeDiretorComercial(),
                        parcelamentoRelatorioHelper.getCpfDiretorComercial(),
                        parcelamentoRelatorioHelper.getProfissao(),
                        parcelamentoRelatorioHelper.getNomeDevedor(),
                        parcelamentoRelatorioHelper.getCnpjDevedor(),
                        parcelamentoRelatorioHelper.getEnderecoDevedor(),
                        parcelamentoRelatorioHelper.getTelefoneDevedor(),
                        parcelamentoRelatorioHelper.getIndicadorPessoaJuridica(),
                        maiorAnoMesReferenciaDocumento,
                        menorAnoMesReferenciaDocumento,
                        //nome do usuario que efetuou o parcelamento 
                        parcelamentoRelatorioHelper.getNomeUsuarioParcelamento(),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoDebitoTotal()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTipoDebito()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoMulta()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoJuros()),
                        Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAtualizacaoMonetaria()),
                        null,null);
    
                //adiciona o bean a cole��o
                relatorioParcelamentosBeans.add(relatorioParcelamentoBean);     
                
            }            
		}

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("contrato", idParcelamento);
		// Empresa
		if (sistemaParametro.getNomeAbreviadoEmpresa() != null) {
			parametros.put("empresa", sistemaParametro.getNomeAbreviadoEmpresa());
		} else {
			parametros.put("empresa", "");
		}
		
		//Titulo Pagina
		if (sistemaParametro.getTituloPagina() != null) {
			parametros.put("tituloPagina", sistemaParametro.getTituloPagina());
		} else {
			parametros.put("tituloPagina", "");
		}

		// CNPJ da Empresa
		if (sistemaParametro.getCnpjEmpresa() != null) {
			
			String cnpjFormatado = sistemaParametro.getCnpjEmpresa().toString();
			String zeros = "";
			
			if (cnpjFormatado != null) {
				
				for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
					zeros = zeros.concat("0");
				}
				// concatena os zeros ao numero
				// caso o numero seja diferente de nulo
				cnpjFormatado = zeros.concat(cnpjFormatado);
				
				cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
						+ cnpjFormatado.substring(2, 5) + "."
						+ cnpjFormatado.substring(5, 8) + "/"
						+ cnpjFormatado.substring(8, 12) + "-"
						+ cnpjFormatado.substring(12, 14);
			}

			parametros.put("cnpj", cnpjFormatado);

		} else {
			parametros.put("cnpj", "");
		}

		// Inscri��o Estadual da Companhia de �gua
		if (sistemaParametro.getInscricaoEstadual() != null) {
			parametros.put("inscricaoEstadual", sistemaParametro.getInscricaoEstadual().toString());
		} else {
			parametros.put("inscricaoEstadual", "");
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioParcelamentosBeans);

		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PARCELAMENTO_CAER,
					parametros, ds, tipoFormatoRelatorio);
		}else if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
            retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PARCELAMENTO_CAERN,
                    parametros, ds, tipoFormatoRelatorio);
		}else if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)){
            retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PARCELAMENTO_CAEMA,
                    parametros, ds, tipoFormatoRelatorio);
        }else if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_JUAZEIRO)){
        	
        	FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
        	filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, idParcelamento));
        	filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID, DebitoTipo.PARCELAMENTO_CONTAS_DIVIDA_ATIVA));
        	
        	Collection colecaoDebitoACobrar = fachada.pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());
        	
        	if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
        		parametros.put("dividaAtiva", "D�VIDA ATIVA");
        	} else {
        		parametros.put("dividaAtiva", "");
        	}
        	
        	retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PARCELAMENTO_JUAZEIRO,
                    parametros, ds, tipoFormatoRelatorio);
        } else if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
        	
        	//Inscri��o Estadual formatada
    		if (sistemaParametro.getInscricaoEstadual() != null) {
    			
    			String inscricaoEstadualFormatada = sistemaParametro.getInscricaoEstadual().toString();
    			
    			if (inscricaoEstadualFormatada != null) {
    				
    				inscricaoEstadualFormatada = inscricaoEstadualFormatada.substring(0, 2) + "."
    						+ inscricaoEstadualFormatada.substring(2, 5) + "/"
    						+ inscricaoEstadualFormatada.substring(5, 8) + "-"
    						+ inscricaoEstadualFormatada.substring(8, 9);
    			}

    			parametros.put("inscricaoEstadual", inscricaoEstadualFormatada);

    		} else {
    			parametros.put("inscricaoEstadual", "");
    		}
        	retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PARCELAMENTO_COSANPA,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_PARCELAMENTO,
					parametros, ds, tipoFormatoRelatorio);
		}
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.PARCELAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioParcelamento", this);
	}
    

}