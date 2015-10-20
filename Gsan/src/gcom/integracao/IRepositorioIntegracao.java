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
* Thiago Silva Toscano de Brito
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

package gcom.integracao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.util.ErroRepositorioException;

public interface IRepositorioIntegracao {

	public Collection pesquisarOrdemServicoMovimentoParaEnvioSAM() throws ErroRepositorioException;

	public void exportarOrdemServicoMovimentos(Collection ordensServicoParaExportacao) throws ErroRepositorioException;

	public Collection pesquisarOrdensServicoParaRecebimentoUPA() throws ErroRepositorioException;

	public void importarOrdensServico(Collection ordensServicoParaImportacao) throws ErroRepositorioException;

	public void atualizarIndicadorMovimentoOrdemServicoMovimento(OrdemServicoMovimento movimento) throws ErroRepositorioException;
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ErroRepositorioException; 
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void exportarDadosContabilidadeIFS(String company,
			String voucher_type,String currency_code,
			String load_id,Date transaction_date,
			String accounting_cod,String cost_center,
			String	accounting_year,String accounting_period,
			BigDecimal debet_amount,BigDecimal credit_amount,
			String	text,String code_c,
			String code_d,String code_g,
			String code_h,String activity_sequence,
			String status,String transaction_type,
			String error_text,Integer record_no,
			Date rowversion)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer valorMaximoChaveIFS()
			throws ErroRepositorioException ;

}
