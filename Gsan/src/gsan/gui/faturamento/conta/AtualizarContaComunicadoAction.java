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
package gsan.gui.faturamento.conta;


import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.ContaComunicado;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.parametrosistema.FiltroParametroSistema;
import gsan.seguranca.parametrosistema.ParametroSistema;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AtualizarContaComunicadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarContaComunicadoActionForm atualizarContaComunicadoActionForm = (AtualizarContaComunicadoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParamentro = fachada.pesquisarParametrosDoSistema();

		String referencia = atualizarContaComunicadoActionForm.getReferencia();
		String titulo = atualizarContaComunicadoActionForm.getTitulo();
		String comunicado = atualizarContaComunicadoActionForm.getComunicado();
		String[] grupoFaturamento = atualizarContaComunicadoActionForm.getGrupoFaturamento();
		String gerenciaRegional = atualizarContaComunicadoActionForm.getGerenciaRegional();
		String localidade = atualizarContaComunicadoActionForm.getLocalidade();
		String[] setorComercial = atualizarContaComunicadoActionForm.getSetorComercial();
		String[] rota = atualizarContaComunicadoActionForm.getRota();
		String[] quadra = atualizarContaComunicadoActionForm.getQuadra();
		String icUso = atualizarContaComunicadoActionForm.getIcUso();
		
/*		if(grupoFaturamento != null && ((grupoFaturamento.length > 0 && !grupoFaturamento[0].isEmpty()) || (grupoFaturamento.length > 1 && grupoFaturamento[0].isEmpty()))){
			gerenciaRegional = null;
			localidade = null;
			setorComercial = null;
			rota = null;
			quadra = null;
		}
		
		if(gerenciaRegional != null && !gerenciaRegional.equals("")){
			grupoFaturamento = null;
			localidade = null;
			setorComercial = null;
			rota = null;
			quadra = null;
		}
		
		if(localidade == null || localidade.equals("")){
			setorComercial = null;
			rota = null;
			quadra = null;
		}else{
			gerenciaRegional = null;
			grupoFaturamento = null;
		}*/
		
		ContaComunicado contaComunicado = new ContaComunicado();
		contaComunicado.setId(new Integer(atualizarContaComunicadoActionForm.getIdComunicado()));
		
		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
		ParametroSistema parametroSistemaCol = null;
		ParametroSistema parametroSistemaLin = null;
		
		filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, ParametroSistema.COMUNICADO_COLUNAS_QTD_MAX));
		Collection<ParametroSistema> colecaoParametroSistemaCol = fachada.pesquisar(filtroParametroSistema, ParametroSistema.class.getName());
		Integer maxColuna = 0;
		if(!colecaoParametroSistemaCol.isEmpty()){
			parametroSistemaCol = colecaoParametroSistemaCol.iterator().next();
			maxColuna = new Integer(parametroSistemaCol.getValorParametro());
		}else{
			throw new ActionServletException("atencao.parametro.sistema.nulo", null, "Quantidade maxima de colunas do comunicado");
		}
		
		filtroParametroSistema.limparListaParametros();
		filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, ParametroSistema.COMUNICADO_LINHAS_QTD_MAX));
		Collection<ParametroSistema> colecaoParametroSistemaLin = fachada.pesquisar(filtroParametroSistema, ParametroSistema.class.getName());
		Integer maxLinha = 0;
		if(!colecaoParametroSistemaLin.isEmpty()){
			parametroSistemaLin = colecaoParametroSistemaLin.iterator().next();
			maxLinha = new Integer(parametroSistemaLin.getValorParametro());
		}else{
			throw new ActionServletException("atencao.parametro.sistema.nulo", null, "Quantidade maxima de linhas do comunicado");
		}
		
		HttpSession sessao = httpServletRequest.getSession(false);				
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		int linhas = 1;
		
		String[] palavrasComunicado = comunicado.split(" ");
		
		String linha = "";
		
		for (int i = 0; i < palavrasComunicado.length; i++) {
			
			//Caso a linha tenha sido quebrada por enter /n , somar 1 a linhas e pular para a proxima linha levando o texto após o /n
			if(palavrasComunicado[i].contains("\n")) {
				String[] quebraLinha = palavrasComunicado[i].split("\r\n");
				
				boolean primeiroEnter = true;
				
				for (int j = 0; j < quebraLinha.length; j++) {
					if (primeiroEnter) {
						primeiroEnter = false;
					} else {
						linhas++;
					}
					
					Object[] dados = verificarQuantidadeLinhasPalavra(linha, quebraLinha[j], maxColuna);
					linhas = linhas + ((Integer) dados[0]);
					//System.out.println(dados[1]);
					linha = "";
				}
				
			} else {
				Object[] dados = verificarQuantidadeLinhasPalavra(linha, palavrasComunicado[i], maxColuna);
				linhas = linhas + ((Integer) dados[0]);
				linha = (String) dados[1];
			}
			
		}
		
//		System.out.println(linhas);
		//Caso o numero de linhas seja maior que a quantidade de linhas aceitadas no comunicado, 
		//maxLinha da tabela parametro sistema, Alertar o usuário que ele ultrapassou o numero maximo de linhas permitidas.
		if(linhas > maxLinha){
			String[] mensagem = new String[2];
			mensagem[0] = "Comunicado da Conta";
			mensagem[1] = "" + maxLinha;
			throw new ActionServletException("atencao.maiorqueomaximo.linhas.permitido", null, mensagem);
		}
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_CONTA_MENSAGEM_INSERIR,
//				new UsuarioAcaoUsuarioHelper(usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_CONTA_MENSAGEM_INSERIR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação
		
		if (Util.validarAnoMes(referencia)){
			throw new ActionServletException(
				"atencao.ano_mes_invalido");
		}else{
			Integer mes = new Integer (referencia.substring(0, 2));
			Integer ano = new Integer (referencia.substring(3, 7));
			
			if (mes <= 0 || mes > 12){
				throw new ActionServletException(
					"atencao.ano_mes_invalido");
			}
			
			if (ano < 1900){
				throw new ActionServletException(
					"atencao.ano_mes_invalido");
			}
			
			if (referencia != null && !referencia.equalsIgnoreCase("")){
				if(sistemaParamentro.getAnoMesFaturamento()<=new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referencia))){
					Integer referenciaFaturametoTratado = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referencia));
					contaComunicado.setAnoMesReferencia(referenciaFaturametoTratado);
				}else{
					throw new ActionServletException(
							"atencao.mes.ano.anterior.atual");
				}
			}
		}
		
		if (referencia != null && !referencia.trim().equals("")) {
			if (Util.validarAnoMes(referencia)) {
				new ActionServletException(
						"atencao.ano_mes_invalido",
						null, referencia);
			}
		}
		
		if (titulo != null && !titulo.equalsIgnoreCase("")){
			contaComunicado.setTitulo(titulo);
		}
		if (comunicado != null && !comunicado.equalsIgnoreCase("")){
			contaComunicado.setComunicado(comunicado);
		}
		
		contaComunicado.setUltimaAlteracao(new java.util.Date());
		
//		 regitrando operacao
//		contaComunicado.setOperacaoEfetuada(operacaoEfetuada);
//		contaComunicado.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(contaComunicado);
		
		contaComunicado.setIndicadorUso(new Short(icUso));
		
//		fachada.removerComunicadoConta(contaComunicado);
		
//		fachada.verificaComunicadoExisteMesmoMesAnoAtualizar(contaComunicado, gerenciaRegional, localidade, grupoFaturamento ,setorComercial, rota, quadra);

		fachada.atualizarContaComunicado(contaComunicado, gerenciaRegional, localidade, grupoFaturamento ,setorComercial, rota, quadra);

		
		montarPaginaSucesso(httpServletRequest, "Comunicado na Conta " + titulo +" atualizada com sucesso.",
				"Atualizar outro Comunicado na Conta",
				"exibirFiltrarContaComunicadoAction.do?menu=sim" );

		
		return retorno;
	}
	
	private Object[] verificarQuantidadeLinhasPalavra(String linha, String palavra, int maxColuna) {

		Object[] retorno = new Object[2];
		
		int qtdLinhas = 0;
		//caso o texto do campo linha mais o tamanho da proxima palavra seja maior que maxColuna
		//somar 1 ao numero de linhas(linhas)
		if (linha.length() + palavra.length() > maxColuna) {
			qtdLinhas++;
			//System.out.println(linha);
			//caso o tamanho da palavra for maior que o maxColuna ou multiplo dele
			//somar a quantidade de linhas e colocar vazio na proxima linha
			if (palavra.length() > maxColuna) {
				int linhasParaSomar = palavra.length() / maxColuna;
				qtdLinhas = qtdLinhas + linhasParaSomar;
				linha = palavra.substring((maxColuna * linhasParaSomar) - 1, palavra.length()) + " ";
			} else {
				linha = palavra + " ";
			}
		} else {
			//Caso a linha seja vazia coloca a palavra + um espaço
			//caso contrario soma a palavra ao texto da linha e coloca um espaço o final
			if (linha.equals("")) {
				linha = palavra + " ";
			} else {
				linha = linha + palavra + " ";
			}
		}
		
		retorno[0] = qtdLinhas;
		retorno[1] = linha;
		
		return retorno;
	}
}