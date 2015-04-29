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
 * Diego Maciel
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


package gsan.seguranca.parametrosistema;
import gsan.interceptor.ObjetoTransacao;
import gsan.seguranca.acesso.Modulo;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

public class ParametroSistema extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;	
	
	public static final String COMUNICADO_LINHAS_QTD_MAX = "QTDMAXLINCOMUNICADO";
	public static final String COMUNICADO_COLUNAS_QTD_MAX = "QTDMAXCOLCOMUNICADO";
	public static final String QTD_MESES_FATURAR_AGUA = "QTMESESFATURARAGUA";
	
	public static final String ID_CORTADOCOMCONSUMO = "ID_CORTADOCOMCONSUMO";
	
	//Refer�ncia Inicial para cobran�a de multa nas contas vencidas
	public static final String REF_INICIAL_COBRANCA_MULTA_CONTAS_VENCIDAS = "REFINICOBMULTACONTA";
	
	public static final String INDICADOR_NEGATIVACAO_POR_GUIA = "ICNEGATIVACAOPORGUIA";

	private Integer id;
	private String nome;
	private String descricao;
	private String codigoConstante;
	private ParametroTipo parametroTipo;
	private String valorParametro;
	private Short indicadorParametroRestrito;
	private Modulo modulo;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ParametroTipo getParametroTipo() {
		return parametroTipo;
	}
	public void setParametroTipo(ParametroTipo parametroTipo) {
		this.parametroTipo = parametroTipo;
	}
	public String getValorParametro() {
		return valorParametro;
	}
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	public Short getIndicadorParametroRestrito() {
		return indicadorParametroRestrito;
	}
	public void setIndicadorParametroRestrito(Short indicadorParametroRestrito) {
		this.indicadorParametroRestrito = indicadorParametroRestrito;
	}
	
	public Modulo getModulo() {
		return modulo;
	}
	
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getCodigoConstante() {
		return codigoConstante;
	}
	public void setCodigoConstante(String codigoConstante) {
		this.codigoConstante = codigoConstante;
	}
	@Override
	public Filtro retornaFiltro() {
		

		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
				filtroParametroSistema.adicionarParametro(
				new ParametroSimples(FiltroParametroSistema.ID, this.getId()));
		return filtroParametroSistema; 
	
	}


	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
