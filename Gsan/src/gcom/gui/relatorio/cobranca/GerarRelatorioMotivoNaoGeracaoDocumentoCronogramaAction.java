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
* Anderson Italo Felinto de Lima
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
package gcom.gui.relatorio.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaGrupoCronogramaMes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.MotivoNaoGeracaoDocumentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioMotivoNaoGeracaoDocumentoCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Anderson Italo
 * @date 26/11/2009
 * Classe respons�vel pelo pr�-precessamento 
 * da chamada do Relatorio Motivo de n�o gera�ao de Documentos de Cobran�a
 * UC9999 Consultar Motivo da n�o Gera��o de Documento de Cobran�a
 */
public class GerarRelatorioMotivoNaoGeracaoDocumentoCronogramaAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
		
		if(form.getDescricaoLocalidade().equalsIgnoreCase("Localidade Inexistente") 
				|| form.getDescricaoSetorComercial().equalsIgnoreCase("Setor Comercial Inexistente")
					|| form.getDescricaoQuadra().equalsIgnoreCase("Quadra Inexistente")){
				throw new ActionServletException("atencao.campos_invalidos");
		}
		
		/*5.	Caso Contr�rio
		 * 5.1.	O sistema exibe os dados para o Comando
		 * (Chamada do Relatorio Motivo de n�o gera�ao de Documentos de Cobran�a)*/
		if ((httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true"))
				||(httpServletRequest.getParameter("filtroPorComandoAnalitico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoAnalitico").equals("true"))){
			
			/*[FS0001] - Validar m�s/ano de refer�ncia
			 * Caso o m�s/ano de refer�ncia n�o seja menor que o m�s/ano do faturamento 
			 * corrente (PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS), 
			 * exibir a mensagem M�s/Ano de Refer�ncia deve ser anterior a << m�s/ano 
			 * do faturamento corrente >> e retornar para o passo correspondente no 
			 * fluxo principal. */
			

			String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getAnoMesReferencia());
			
			//String anoMesFaturamentoCorrente = ""+ sistemaParametro.getAnoMesFaturamento();
			Date dataCorrente = new Date();
			
			String anoMesFaturamentoCorrente = Util.getAnoMesComoString(dataCorrente);
			
			Integer resultado = anoMesReferencia.compareTo(anoMesFaturamentoCorrente);
			
			if (resultado > 0){
				
				throw new ActionServletException( "atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
						null, 
//						Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
						Util.formatarAnoMesParaMesAno(anoMesFaturamentoCorrente));
			}
			
			/*2.	O sistema obt�m a identifica��o do comando (CAAC_ID da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG 
			 * com CBAT_ID = 2"CBAT_ID da atividade informada" e CBCR_ID = CBCR_ID da tabela COBRANCA_ACAO_CRONOGRAMA com CBAC_ID = CBAC_ID 
			 * da A��o informada e CBCM_ID = CBCM_ID da tabela COBRANCA_GRUPO_CRONOGRAMA_MES com CBCM_AMREFERENCIA= ano/m�s de referencia informado 
			 * e CBGR_ID = CBGR_ID do grupo informado.*/
			FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMes = new FiltroCobrancaGrupoCronogramaMes();
			filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupoCronogramaMes.ANO_MES_REFERENCIA, anoMesReferencia));
			filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupoCronogramaMes.ID_COBRANCA_GRUPO, new Integer(form.getIdCobrancaGrupo())));
			
			Collection colecaoCobrancaGrupoCronogramaMes = fachada.pesquisar(filtroCobrancaGrupoCronogramaMes, CobrancaGrupoCronogramaMes.class.getName());
			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) Util.retonarObjetoDeColecao(colecaoCobrancaGrupoCronogramaMes);
			
			if (colecaoCobrancaGrupoCronogramaMes != null && !colecaoCobrancaGrupoCronogramaMes.isEmpty()){
				FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
				filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAcaoCronograma.ID_COBRANCA_ACAO, new Integer(form.getIdCobrancaAcao())));
				filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES, cobrancaGrupoCronogramaMes.getId()));
				
				Collection colecaoCobrancaAcaoCronograma = fachada.pesquisar(filtroCobrancaAcaoCronograma, CobrancaAcaoCronograma.class.getName());
				CobrancaAcaoCronograma cobrancaAcaoCronograma = (CobrancaAcaoCronograma) Util.retonarObjetoDeColecao(colecaoCobrancaAcaoCronograma);
						
				if (colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()){
					FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
					filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE, new Integer(form.getIdCobrancaAtividade())));
					filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA, cobrancaAcaoCronograma.getId()));
					
					Collection colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
					cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) Util.retonarObjetoDeColecao(colecaoCobrancaAcaoAtividadeCronograma);
				}
			}
			
			if (cobrancaAcaoAtividadeCronograma == null){
				throw new ActionServletException("atencao.comando_inexistente_parametros_informados");
			}
		}
		
		
		Usuario usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		
		RelatorioMotivoNaoGeracaoDocumentoCobranca relatorio = new RelatorioMotivoNaoGeracaoDocumentoCobranca(usuario);
		relatorio.addParametro("cobrancaAcaoAtividadeCronograma", cobrancaAcaoAtividadeCronograma);
		relatorio.addParametro("idCobrancaGrupo", new Integer(form.getIdCobrancaGrupo()));
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		relatorio.addParametro("indicadorCronograma", 1);
		relatorio.addParametro("form",form);
		
		if(httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true")){
			relatorio.addParametro("sintetico", 1);
		}else{
			relatorio.addParametro("sintetico", 2);
		}
		
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		
	}

}
