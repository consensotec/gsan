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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
 * 
 * @author Daniel Alves,Hugo Amorim
 * @date 28/09/2010,04/10/2010
 */

public class RelatorioImoveisClientesCorporativo extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioImoveisClientesCorporativo(Usuario usuario) {		
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_IMOVEL);
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
			
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		Collection<Imovel> colecaoImoveis = (Collection<Imovel>) getParametro("colecaoImoveisCorporativos");

		List relatorioBeans = new ArrayList();
		

		// coleção de beans do relatório
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		for(Imovel imoveis: colecaoImoveis){
			
			Collection<Object[]> clientes = (Collection<Object[]>) Fachada.getInstancia().obterClientePorImovel(imoveis.getId());
												
			RelatorioImoveisClientesCorporativoBean relatorioBean = null;
			
			if(clientes != null){
				for(Object[] cliente : clientes){
					relatorioBean = new RelatorioImoveisClientesCorporativoBean();
					
					relatorioBean.setCliente((String) cliente[2]);
					
	//				// ------------ REGISTRAR TRANSAÇÃO ----------------
	//				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
			//Operacao.OPERACAO_ATUALIZAR_CUSTO_PAVIMENTO, logradouro.getId(),
	//				logradouro.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
	//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	// ------------ REGISTRAR TRANSAÇÃO ----------------
	
	
			
					relatorioBean.setMatricula(imoveis.getId()+"");
					
					// Filltro para setar descricao relacao tipo
					FiltroClienteRelacaoTipo filtroRelacao =  new FiltroClienteRelacaoTipo();			
					filtroRelacao.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID, cliente[1]));
					
					Collection<ClienteRelacaoTipo> clientesRelacao = Fachada.getInstancia().pesquisar(filtroRelacao,ClienteRelacaoTipo.class.getName());
					
					for(ClienteRelacaoTipo clienteRelacao : clientesRelacao){
						relatorioBean.setTipoCliente(clienteRelacao.getDescricao());
					}
					
					relatorioBeans.add(relatorioBean);
				}
			}
		}
		
		
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
				
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) relatorioBeans );
		String tipoFormatoRelatorio = (String) getParametro("tipoRelatorio");
		
		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_IMOVEIS_CLIENTES_CORPORATIVO, parametros, ds,
					new Integer(tipoFormatoRelatorio));
		
 
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_IMOVEIS_CLIENTES_CORPORATIVO,
						idFuncionalidadeIniciada);

		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {	
			
		int retorno =1;
				
		return retorno;
	}
	
	

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisDoacoesImovel", this);
	}
	
}
