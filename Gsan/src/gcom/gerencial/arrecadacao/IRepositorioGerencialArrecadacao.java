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
package gcom.gerencial.arrecadacao;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * 
 * @author Ivan S�rgio
 * @created 10/05/2007
 */
public interface IRepositorioGerencialArrecadacao {
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Agu�/Esgoto]
	 * 
	 * @author Ivan S�rgio
	 * @date 19/05/2008
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgoto(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - CONTA]
	 * 
	 * @author Ivan S�rgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosConta(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - GUIA DE PAGAMENTO]
	 * 
	 * @author Ivan S�rgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamento(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - DEBITO A COBRAR]
	 * 
	 * @author Ivan S�rgio
	 * @date 20/05/2008
	 * 
	 * @param idSetorComercial
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrar(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * @author Ivan S�rgio
	 * @date 22/05/2008
	 *
	 * @param idSetorComercial
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditos(int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior m�s/ano de refer�ncia da tabela un_resumo_arrecadacao
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoArrecadacao()
			throws ErroRepositorioException;
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Agu�/Esgoto - Valor Nao Identificado]
	 * 
	 * @author Ivan S�rgio
	 * @date 02/06/2008
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado(
			int idSetorComercial, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * [UC0533] - Gerar Resumo da Arrecadacao
	 * 
	 * @author Ivan S�rgio
	 * @date 12/06/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebito(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/***
	 * [UC0533] - Gerar Resumo da Arrecadacao - Devolucao
	 * 
	 * @author Ivan S�rgio
	 * @date 09/10/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucao(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 02/06/2010
	 * 		 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * Gerar Resumo da Arrecadacao Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 03/06/2010
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebitoPorAno(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Agu�/Esgoto - Valor Nao Identificado
	 * 
	 * @author Fernando Fontelles
	 * @date 05/06/2010
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificadoPorAno(
			int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - CONTA
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosContaPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - GUIA DE PAGAMENTO
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamentoPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - DEBITO A COBRAR
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrarPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/**
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditosPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException;
	
	/***
	 * Gerar Resumo da Arrecadacao Por Ano - Devolucao
	 * 
	 * @author Fernando Fontelles
	 * @date 10/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucaoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
	/***
	 * Carrega os dados do resumo da arrecada��o do gerencial
	 * 
	 * @author Erivan Sousa
	 * @date 19/04/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */
	public void executarCargaResumoArrecadacaoGerencialPorAno()
			throws ErroRepositorioException, SQLException;
	
	/**
	 *
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaLigacoesEconomiasPorAno()
			throws ErroRepositorioException, SQLException;
	
	/**
	 *
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaIndicadorLigacaoEconomiaPorAno()
			throws ErroRepositorioException, SQLException;
	
	/**
	 *
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaResumoParcelamentoPorAno()
			throws ErroRepositorioException, SQLException;
	
	/**
	 *
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaResumoPendenciaPorAno()
			throws ErroRepositorioException, SQLException;
	
	/**
	 *
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaPendenciaSemQuadraVariosAnos()
			throws ErroRepositorioException, SQLException;
	
	/**
	 * 
	 * @author Rafael Corr�a
	 * @date 17/04/2013
	 *
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */
	public void gerarResumoArrecadacao() throws ErroRepositorioException, SQLException;
	
	
	/**
	 * Batch criado para migrar todos os resumos analiticos 
	 * do banco comercial para o gerencial
	 * 
	 * @autor Bruno Barros
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */		
	public void migrarResumosAnaliticos() throws ErroRepositorioException, SQLException;	

}