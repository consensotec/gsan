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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0802] - Filtrar Debito Autom�tico
 * 
 * @author Bruno Barros
 * @date 11/06/2008
 */
public class FiltrarDebitoAutomaticoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("exibirManterDebitoAutomatico");

		FiltrarDebitoAutomaticoActionForm form = (FiltrarDebitoAutomaticoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Iniciamos a montagem do filtro
		FiltroDebitoAutomatico filtroDebitoAutomatico = new FiltroDebitoAutomatico();
		// Trazemos apenas os debitos com data de exclus�o nula
		filtroDebitoAutomatico.adicionarParametro( new ParametroNulo( FiltroDebitoAutomatico.DATA_EXCLUSAO  ) );
		
		// Verificamos se algum parametro foi informado, e em caso positivo
		// adicionamos ao filtro, caso contrario, informamos que ao menos 1
		// parametro precisa ser informado
		boolean bInformouParametro = false;
		
		// Informou matricula ????
		if ( form.getMatricula() != null && !form.getMatricula().equals( "" ) ){
			bInformouParametro = true;
			
			// Uma vez que a matricula foi informada, verificamos se a mesma � valida
			// FS0001 - Verificar existencia de d�bito autom�tico para o imovel
			if ( !verificaExistenciaDebitoAutomatico( form.getMatricula() ) ){
				throw new ActionServletException( "atencao.imovel_sem_debito_automatico_cadastrado" );
			}
			
			filtroDebitoAutomatico.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.IMOVEL_MATRICULA, form.getMatricula() ) );			
		} else {		
			// Informou agencia ????
			if ( form.getAgenciaCodigo() != null && !form.getAgenciaCodigo().equals( "" ) ){
				bInformouParametro = true;
				
				// Uma vez que a agencia foi informada, verificamos se a mesma � v�lida
				// FS0003 - Verificar existencia da ag�ncia
				if ( !verificaCodigoAgenciaValido( form.getAgenciaCodigo() ) ){
					throw new ActionServletException( "atencao.agenciaBancaria.inexistente" );
				}			
				
				filtroDebitoAutomatico.adicionarCaminhoParaCarregamentoEntidade( "agencia" );
				filtroDebitoAutomatico.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.AGENCIA_CODIGO, form.getAgenciaCodigo() ) );			
			}
			
			// Informou banco ????
			if ( form.getBancoID() != null && !form.getBancoID().equals( "" ) ){
				bInformouParametro = true;
				
				// uma vez que o banco foi informado, verificamos se a mesma � valida
				// FS0002 - Verificar exist�ncia do banco
				if ( !verificaExistenciaBancoId( form.getBancoID() ) ){
					throw new ActionServletException( "atencao.banco.inexistente" );
				}						
				filtroDebitoAutomatico.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.BANCO_ID, form.getBancoID() ) );			
			}
		}
		
		// Verificamos se ao menos 1 parametro foi informado
		// FS0004 - Verificar Preenchimento dos campos
		if ( !bInformouParametro ){
			throw new ActionServletException( "atencao.filtro.nenhum_parametro_informado" );
		}		

		// Verificamos se a agencia informada pertence ao banco informado
		if ( form.getAgenciaCodigo() != null && !form.getAgenciaCodigo().equals( "" ) ){
			verificaAgenciaPertenceAoBanco( form.getBancoID(), form.getAgenciaCodigo() );
		}
		
		// Verificamos se a pesquisar ir� retornar algum resultado
		// FS0005 - Nenhum resultado encontrado
		if ( verificarRegistrosGeradosZerados( filtroDebitoAutomatico ) ){
			throw new ActionServletException( "atencao.pesquisa.nenhumresultado" );
		}		

		// FS0006 - Verica se, informada a agencia, tambem informou o banco
		// Manda o filtro pelo request para o ExibirManterDebitoAutomatico
		sessao.setAttribute("filtroDebitoAutomatico", filtroDebitoAutomatico);

		return retorno;
	}
	
	// FS0001 - Verificar existencia de d�bito autom�tico para o imovel 
	private boolean verificaExistenciaDebitoAutomatico( String matricula ){
		// Montamos o filtro para verificar se a matricula do imovel
		// possue algum registro na tabela de debito automatico com
		// data de exclus�o diferente de nulo
		FiltroDebitoAutomatico filtro = new FiltroDebitoAutomatico();
		filtro.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.IMOVEL_MATRICULA, matricula ) );
		filtro.adicionarParametro( new ParametroNulo( FiltroDebitoAutomatico.DATA_EXCLUSAO ) );		
		Collection<DebitoAutomatico> colDebitos = Fachada.getInstancia().pesquisar( filtro, DebitoAutomatico.class.getName() );
		
		return colDebitos != null && colDebitos.size() > 0;
	}
	
	// FS0002 - Verificar existencia do banco 
	private boolean verificaExistenciaBancoId( String bancoId ){
		// Montamos o filtro para verificar se o banco existe
		FiltroBanco filtro = new FiltroBanco();
		filtro.adicionarParametro( new ParametroSimples( FiltroBanco.ID, bancoId ) );		
		Collection<Banco> colBancos = Fachada.getInstancia().pesquisar( filtro, Banco.class.getName() );
		
		return colBancos != null && colBancos.size() > 0;
	}	
	
	// FS0003 - Verificar existencia da agencia bancaria 
	private boolean verificaCodigoAgenciaValido( String agenciaCodigo ){
		// Montamos o filtro para verificar se a agencia banc�ria existe
		FiltroAgencia filtro = new FiltroAgencia();
		filtro.adicionarParametro( new ParametroSimples( FiltroAgencia.CODIGO_AGENCIA, agenciaCodigo ) );		
		Collection<Agencia> colAgencias = Fachada.getInstancia().pesquisar( filtro, Agencia.class.getName() );
		
		return colAgencias != null && colAgencias.size() > 0;
	}
	
	// FS0005 - Nenhum registro encontrado
	private boolean verificarRegistrosGeradosZerados( FiltroDebitoAutomatico filtro ){
		// Pesquisamos
		Collection<DebitoAutomatico> colDebito =  Fachada.getInstancia().pesquisar( filtro, DebitoAutomatico.class.getName() );
		return colDebito == null || colDebito.size() == 0;
	}
	
	// FS0006 - Verica se, informada a agencia, tambem informou o banco
	// e se a agencia pertence aquele banco
	private void verificaAgenciaPertenceAoBanco( String bancoId, String agenciaCodigo ) throws ActionServletException{
		// Caso banco seja nulo, informar que ele e obrigatorio com a agencie
		if ( bancoId == null || bancoId.equals( "" ) ){
			throw new ActionServletException( "atencao.agencia.sem_banco" );
		}
		
		// Verificamo se a agencia pertence ao banco informado
		FiltroAgencia filtro = new FiltroAgencia();
		filtro.adicionarParametro( new ParametroSimples( FiltroAgencia.CODIGO_AGENCIA, agenciaCodigo ) ); 
		filtro.adicionarParametro( new ParametroSimples( FiltroAgencia.BANCO_ID, bancoId ) );
		
		Collection<DebitoAutomatico> colDebitos = Fachada.getInstancia().pesquisar( filtro, Agencia.class.getName() ); 
		
		if ( colDebitos == null || colDebitos.size() == 0 ){
			throw new ActionServletException( "atencao.agencia.banco_errado" );
		}
	}
	
}
