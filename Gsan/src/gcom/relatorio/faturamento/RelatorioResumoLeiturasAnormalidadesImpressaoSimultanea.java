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
package gcom.relatorio.faturamento;

import gcom.relatorio.ConstantesRelatorios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;


/**
 * 
 * Este caso de uso permite a inser��o de dados na tabela movimento 
 * conta pr�-faturada.
 *
 * [UC0923] Incluir Movimento Conta Pr�-Faturada
 *
 *
 *  Caso seja chamado por uma tela, o sistema gera uma tela de acordo com o 
 *  movimento, Caso contr�rio, o sistema gera um relat�rio e envia, por 
 *  e-mail para o operador, registrado com os seguintes campos:
 *  
 *  No cabe�alho imprimir o grupo de faturamento informado (FTGR_ID), o 
 *  c�digo e descri��o da empresa (EMPR_ID e EMPR_NMEMPRESA da tabela 
 *  EMPRESA com EMPR_ID da tabela ROTA com ROTA_ID da tabela QUADRA com 
 *  QDRA_ID da tabela IMOVEL com IMOV_ID=matr�cula do im�vel do primeiro 
 *  registro do arquivo que exista na tabela IMOVEL), o c�digo da localidade 
 *  e o t�tulo fixo �MOVIMENTO CELULAR - IMPRESS�O SIMULT�NEA� quando 
 *  processado o arquivo de movimento;
 *  
 *  Imprimir o erro correspondente encontrado no processamento do im�vel;
 *  
 *  Caso seja chamado por uma tela, imprimir um texto �Arquivo processado 
 *  com problema e enviado para opera��o para processar o movimento. 
 *  Localidade <<C�digo da Localidade>>�;    
 *
 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impress�o 
 * simult�nea com Problemas
 *
 * @author bruno
 * @date 30/06/2009
 *
 * @param colErrors 
 */ 
public class RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea extends TarefaRelatorio {
    
	private static final long serialVersionUID = 1L;
	
	private byte[] relatorio = null; 
    
	public RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA );
	}
	
	@Deprecated
	public RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea() {
		super(null, "");
	}
	public Object executar() throws TarefaException {
        return relatorio;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

        return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("relatorioResumoLeiturasAnormalidadesRegistradas", this);
	}

	public byte[] getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(byte[] relatorio) {
		this.relatorio = relatorio;
	}

}