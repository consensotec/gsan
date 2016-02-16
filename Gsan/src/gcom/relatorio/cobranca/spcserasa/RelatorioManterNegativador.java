
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
package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 28 de Fevereiro de 2008
 * @version 1.0
 */

public class RelatorioManterNegativador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativador object
	 */
	public RelatorioManterNegativador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR);
	}

	@Deprecated
	public RelatorioManterNegativador() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param Negativador Parametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os par�metros que ser�o utilizados no relat�rio
		FiltroNegativador  filtroNegativador  = (FiltroNegativador) getParametro("filtroNegativador");
		Negativador negativadorParametros = (Negativador) getParametro("negativadorParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterNegativadorBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necess�rios para
		// a impress�o do relat�rio
		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);
		filtroNegativador.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection<Negativador> colecaoNegativadorNovos = fachada.pesquisar(filtroNegativador, Negativador.class
				.getName());

		if (colecaoNegativadorNovos != null && !colecaoNegativadorNovos.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			for (Negativador negativador : colecaoNegativadorNovos) {
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				// ID
				String id = "";
				
				if (negativador.getId() != null) {
					id = negativador.getId().toString();
				}
				
				// c�digo agente
				String codigoAgente = null;
				
				if (negativador.getCodigoAgente() != null) {
					codigoAgente = negativador.getCodigoAgente().toString();
				}
								
				// c�digo do cliente
				String cliente = "";				
				if (negativador.getCliente().getId() != null) {
					cliente = negativador.getCliente().getId().toString();
				}
							
				// nome do negativador 
				String nome = "";				
				if (negativador.getCliente() != null) {					
					nome = negativador.getCliente().getNome();
				}
				
				// im�vel
				String imovel = "";				
				if (negativador.getImovel()!= null && negativador.getImovel().getId() != null) {
					imovel = negativador.getImovel().getId().toString();
				}

				// numeroInscricaoEstadual
				String numeroInscricaoEstadual = "";				
//				if (negativador.getImovel()!= null && negativador.getImovel().getInscricaoFormatada()!= null) {
//					numeroInscricaoEstadual = negativador.getImovel().getInscricaoFormatada();
//				}
				
				// indicadorUso
				String indicadorUso = "";				
				if (negativador.getIndicadorUso()!= null ) {
					if(negativador.getIndicadorUso().equals(ConstantesSistema.SIM)){
						indicadorUso = "Ativo";
					}else if(negativador.getIndicadorUso().equals(ConstantesSistema.NAO)){
						indicadorUso = "Inativo";
					}
				}
				
				// Inicializa o construtor constitu�do dos campos
				// necess�rios para a impress�o do relatorio
				relatorioBean = new RelatorioManterNegativadorBean(						
						// ID
						id,
						
						// Descri��o do Negativador
						codigoAgente,	
						
						// N�mero do Contrato
						cliente,
											
						// Data In�cio
						nome,
						
						// Data Fim
						imovel,
						
						numeroInscricaoEstadual,
						
						indicadorUso);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (negativadorParametros.getCodigoAgente() != null) {
			parametros.put("codigoAgente", negativadorParametros.getCodigoAgente().toString());
		} else {
			parametros.put("codigoAgente", "");
		}
			
		if (negativadorParametros.getCliente().getId() != null) {
			parametros.put("codigoCliente", negativadorParametros.getCliente().getId().toString());
		} else {
			parametros.put("codigoCliente", "");
		}
		
		if (negativadorParametros.getImovel().getId() != null) {
			parametros.put("imovel", negativadorParametros.getImovel().getId().toString());
		} else {
			parametros.put("imovel", "");
		}
		
		if (negativadorParametros.getNumeroInscricaoEstadual() != null) {
			parametros.put("numeroInscricaoEstadual", negativadorParametros.getNumeroInscricaoEstadual());
		} else {
			parametros.put("numeroInscricaoEstadual", "");
		}

		if (negativadorParametros.getIndicadorUso() != null) {
			if (negativadorParametros.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("indicadorUso", "Ativo");
			} else {
				parametros.put("indicadorUso", "Inativo");
			}
			
		} else {
			parametros.put("indicadorUso", "");
		}
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_NEGATIVADOR,
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroNegativador) getParametro("filtroNegativador"),
				Negativador.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNegativador", this);
	}

}
