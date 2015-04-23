/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestï¿½o de Serviï¿½os de Saneamento
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
 * GSAN - Sistema Integrado de Gestï¿½o de Serviï¿½os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araï¿½jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Clï¿½udio de Andrade Lira
 * Denys Guimarï¿½es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabï¿½ola Gomes de Araï¿½jo
 * Flï¿½vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Jï¿½nior
 * Homero Sampaio Cavalcanti
 * Ivan Sï¿½rgio da Silva Jï¿½nior
 * Josï¿½ Edmar de Siqueira
 * Josï¿½ Thiago Tenï¿½rio Lopes
 * Kï¿½ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Mï¿½rcio Roberto Batista da Silva
 * Maria de Fï¿½tima Sampaio Leite
 * Micaela Maria Coelho de Araï¿½jo
 * Nelson Mendonï¿½a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrï¿½a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araï¿½jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sï¿½vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa ï¿½ software livre; vocï¿½ pode redistribuï¿½-lo e/ou
 * modificï¿½-lo sob os termos de Licenï¿½a Pï¿½blica Geral GNU, conforme
 * publicada pela Free Software Foundation; versï¿½o 2 da
 * Licenï¿½a.
 * Este programa ï¿½ distribuï¿½do na expectativa de ser ï¿½til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implï¿½cita de
 * COMERCIALIZAï¿½ï¿½O ou de ADEQUAï¿½ï¿½O A QUALQUER PROPï¿½SITO EM
 * PARTICULAR. Consulte a Licenï¿½a Pï¿½blica Geral GNU para obter mais
 * detalhes.
 * Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU
 * junto com este programa; se nï¿½o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gsan.micromedicao;

import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ItemServicoContrato extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR = 1661; //Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private ItemServico itemServico;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private BigDecimal valor;

	/** nullable persistent field */
	@ControleAlteracao(value=FiltroItemServicoContrato.CONTRATO_EMPRESA_SERVICO_ID, funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private ContratoEmpresaServico contratoEmpresaServico;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date ultimaAlteracao;

	private ServicoTipo servicoTipo;
	
	private BigDecimal percentualServicosNaoExecutados;
	
	private BigDecimal percentualMultaSerAplicada;
	
	private BigDecimal quantidadeOrcadaItemContrato;
	
	private BigDecimal valorOrcadoItemContrato;
	
	private Collection servicoTipos;
	
	private String hint;
	
	
	public Collection getServicoTipos() {
		return servicoTipos;
	}

	public void setServicoTipos(Collection servicoTipos) {
		this.servicoTipos = servicoTipos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** full constructor */
	public ItemServicoContrato(ItemServico itemServico, BigDecimal valor, 
			ContratoEmpresaServico contratoEmpresaServico, Date ultimaAlteracao) {
		
		this.itemServico = itemServico;
		this.valor = valor;
		this.contratoEmpresaServico = contratoEmpresaServico;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ItemServicoContrato() {
		
	}

	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}

	public void setContratoEmpresaServico(
			ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemServico getItemServico() {
		return itemServico;
	}

	public void setItemServico(ItemServico itemServico) {
		this.itemServico = itemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	@Override
	public void initializeLazy() {
		
		if (getContratoEmpresaServico() != null) contratoEmpresaServico.initializeLazy();
		if (getItemServico() != null) itemServico.initializeLazy();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"contratoEmpresaServico.descricaoContrato", "itemServico.descricao", "valor"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao Contrato", "Item Servico", "Valor"};
		return labels;		
	}
	
	@Override
	public Filtro retornaFiltro() {
		
		FiltroItemServicoContrato filtroItemServicoContrato = new FiltroItemServicoContrato();
		
		filtroItemServicoContrato.adicionarParametro(new ParametroSimples(FiltroItemServicoContrato.ID,
				this.getId()));

		return filtroItemServicoContrato;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroItemServicoContrato.CONTRATO_EMPRESA_SERVICO);
		return filtro;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
	 public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}

	public BigDecimal getPercentualServicosNaoExecutados() {
		return percentualServicosNaoExecutados;
	}

	public void setPercentualServicosNaoExecutados(
			BigDecimal percentualServicosNaoExecutados) {
		this.percentualServicosNaoExecutados = percentualServicosNaoExecutados;
	}

	public BigDecimal getPercentualMultaSerAplicada() {
		return percentualMultaSerAplicada;
	}

	public void setPercentualMultaSerAplicada(BigDecimal percentualMultaSerAplicada) {
		this.percentualMultaSerAplicada = percentualMultaSerAplicada;
	}

	public BigDecimal getQuantidadeOrcadaItemContrato() {
		return quantidadeOrcadaItemContrato;
	}

	public void setQuantidadeOrcadaItemContrato(BigDecimal quantidadeOrcadaItemContrato) {
		this.quantidadeOrcadaItemContrato = quantidadeOrcadaItemContrato;
	}

	public BigDecimal getValorOrcadoItemContrato() {
		return valorOrcadoItemContrato;
	}

	public void setValorOrcadoItemContrato(BigDecimal valorOrcadoItemContrato) {
		this.valorOrcadoItemContrato = valorOrcadoItemContrato;
	}	
	
	public String getHint() {
		String hint = "<strong>Tipos de Serviço:</strong><br> ";
		if(this.servicoTipos != null && !this.servicoTipos.isEmpty()){
			Iterator it = this.servicoTipos.iterator();
			while(it.hasNext()){
				ServicoTipo servicoTipo = (ServicoTipo)it.next();
				hint += servicoTipo.getId()+" - "+servicoTipo.getDescricao()+"<br>";
			}
		}
		
		return hint;
	}
	
}