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
package gcom.atendimentopublico.portal;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class AcessoLojaVirtual extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String numeroCodigoAtendimento;
	
	private String ipAcesso;
	
	private Date ultimaAlteracao;
	
	private Short indicadorServicoExecutado;

	//Modulo: Canais de atendimento
	public final static String LOJAS_ATENDIMENTO_PRESENCIAL = new String("1");
	
	public final static String TELE_ATENDIMENTO = new String("2");
	
	public final static String OUVIDORIA = new String("3");

	//Modulo: Informações
	public final static String NEGOCIACAO_DEBITO = new String("4");

	public final static String ESTRUTURA_TARIFARIA = new String("5");
	
	public final static String VALIDAR_CERTIDAO_NEGATIVA = new String("6");

	public final static String VOLUME_CONSUMIDO = new String("7");

	public final static String TABELA_SERVICOS = new String("8");

	public final static String ONDE_PAGAR_FATURA = new String("9");
	
	public final static String ORIENTACOES_CLIENTE = new String("10");
	
	public final static String DEBITO_AUTOMATICO = new String("11");

	
	
	//Modulo: Serviços
	public final static String SEGUNDA_VIA_CONTA = new String("12");
	
	public final static String DECLARACAO_ANUAL_QUITACAO_DEBITO = new String("13");
	
	public final static String OUTROS_SERVICOS = new String("14");
		
	public final static String CONSULTAR_PAGAMENTOS = new String("15");
	
	public final static String ACOMPANHAR_REGISTRO_ATENDIMENTO = new String("16");	
	
	public final static String CONSULTAR_CONSUMO_HISTORICO = new String("17");
	
	public final static String ALTERAR_VENCIMENTO = new String("18");
	
	public final static String CERTIDAO_NEGATIVA_DEBITOS = new String("19");
	
	public final static String RECEBIMENTO_FATURA_EMAIL = new String("20");
	
	public final static String SOLICITAR_CONTA_BRAILLE = new String("21");
	
	//indicadorServicoExecutado
	
	public final static Short INDICADOR_SERVICO_EXECUTADO 	  = new Short("1");
	
	public final static Short INDICADOR_SERVICO_NAO_EXECUTADO = new Short("2");
	
	public AcessoLojaVirtual(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroCodigoAtendimento() {
		return numeroCodigoAtendimento;
	}

	public void setNumeroCodigoAtendimento(String numeroCodigoAtendimento) {
		this.numeroCodigoAtendimento = numeroCodigoAtendimento;
	}

	public String getIpAcesso() {
		return ipAcesso;
	}

	public void setIpAcesso(String ipAcesso) {
		this.ipAcesso = ipAcesso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorServicoExecutado() {
		return indicadorServicoExecutado;
	}

	public void setIndicadorServicoExecutado(Short indicadorServicoExecutado) {
		this.indicadorServicoExecutado = indicadorServicoExecutado;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}
	
}