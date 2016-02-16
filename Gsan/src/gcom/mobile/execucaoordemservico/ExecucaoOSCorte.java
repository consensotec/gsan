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
package gcom.mobile.execucaoordemservico;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ExecucaoOSCorte extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private ArquivoTextoOSCobranca arquivoTextoOSCobranca;
    private OrdemServico ordemServico;
    private Integer leituraCorte;
    private Date ultimaAlteracao;
    private MotivoCorte motivoCorte;
    private CorteTipo corteTipo;
    private Integer numeroSelo;
    private Short codigoPavimento;
	private Short codigotipoPavimento;
	private Short indicadorExisteCalcada;
	
    /** full constructor */
    public ExecucaoOSCorte(ArquivoTextoOSCobranca arquivoTextoOSCobranca, OrdemServico ordemServico, Integer leituraCorte, Date ultimaAlteracao, MotivoCorte motivoCorte, CorteTipo corteTipo, Integer numeroSelo,	Short codigoPavimento, Short codigotipoPavimento, Short indicadorExisteCalcada) {
        this.arquivoTextoOSCobranca = arquivoTextoOSCobranca;
        this.ordemServico = ordemServico;
    	this.leituraCorte = leituraCorte;
        this.ultimaAlteracao = ultimaAlteracao;
        this.motivoCorte = motivoCorte;
        this.corteTipo = corteTipo;
        this.numeroSelo = numeroSelo;
    	this.codigoPavimento = codigoPavimento;
    	this.codigotipoPavimento = codigotipoPavimento;
    	this.indicadorExisteCalcada = indicadorExisteCalcada;
    }
    
    public ExecucaoOSCorte( Integer idArquivoTexto, String linha ){
    	List<String> colunas = Util.split(linha);
    	
    	Integer idOs = Integer.parseInt(colunas.get(1));
    	
        ArquivoTextoOSCobranca atoc = new ArquivoTextoOSCobranca();
        atoc.setId( idArquivoTexto );
        this.arquivoTextoOSCobranca = atoc;
        
        OrdemServico os = new OrdemServico();
        os.setId( idOs );
        this.ordemServico = os;
        
        MotivoCorte mc = new MotivoCorte();
        mc.setId( new Integer( colunas.get(2) ) );
        this.setMotivoCorte(mc);
        
        CorteTipo ct = new CorteTipo();
        Integer idCorteTipo = new Integer( colunas.get(3) );
        ct.setId( idCorteTipo);
        this.setCorteTipo(ct);
        
        if ( colunas.get(4) != null && !colunas.get(4).equals("") ){
        	this.setLeituraCorte( new Integer( colunas.get(4) ) );	
        }
        
        if ( colunas.get(5) != null && !colunas.get(5).equals("")) {                
        	this.setNumeroSelo( new Integer( colunas.get(5) ) );
        }
        
        if(idCorteTipo.equals(CorteTipo.CORTE_COM_PAVIMENTACAO)){
        	this.setCodigoPavimento(new Short("1"));
        }else if(idCorteTipo.equals(CorteTipo.CORTE_SEM_PAVIMENTACAO)){
        	this.setCodigoPavimento(new Short("2"));
        }else if(idCorteTipo.equals(CorteTipo.CORTE_NICHO)){
        	this.setCodigoPavimento(new Short("3"));
        }
        
        if ( colunas.get(6) != null && !colunas.get(6).equals("")) {                
        	this.setCodigotipoPavimento( new Short ( colunas.get(6) ) );
        }
        if ( colunas.get(7) != null && !colunas.get(7).equals("")) {                
        	this.setIndicadorExisteCalcada( new Short ( colunas.get(7) ) );
        }

        this.setUltimaAlteracao( new Date() );
    }

    /** default constructor */
    public ExecucaoOSCorte() {
    }
    
	public Integer getLeituraCorte() {
		return leituraCorte;
	}

	public void setLeituraCorte(Integer leituraCorte) {
		this.leituraCorte = leituraCorte;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public MotivoCorte getMotivoCorte() {
		return motivoCorte;
	}

	public void setMotivoCorte(MotivoCorte motivoCorte) {
		this.motivoCorte = motivoCorte;
	}

	public CorteTipo getCorteTipo() {
		return corteTipo;
	}

	public void setCorteTipo(CorteTipo corteTipo) {
		this.corteTipo = corteTipo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArquivoTextoOSCobranca getArquivoTextoOSCobranca() {
		return arquivoTextoOSCobranca;
	}

	public void setArquivoTextoOSCobranca(ArquivoTextoOSCobranca arquivoTextoOSCobranca) {
		this.arquivoTextoOSCobranca = arquivoTextoOSCobranca;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Short getCodigoPavimento() {
		return codigoPavimento;
	}

	public void setCodigoPavimento(Short codigoPavimento) {
		this.codigoPavimento = codigoPavimento;
	}

	public Short getCodigotipoPavimento() {
		return codigotipoPavimento;
	}

	public void setCodigotipoPavimento(Short codigotipoPavimento) {
		this.codigotipoPavimento = codigotipoPavimento;
	}

	public Short getIndicadorExisteCalcada() {
		return indicadorExisteCalcada;
	}

	public void setIndicadorExisteCalcada(Short indicadorExisteCalcada) {
		this.indicadorExisteCalcada = indicadorExisteCalcada;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		
		return null;
	}

	public Integer getNumeroSelo() {
		return numeroSelo;
	}

	public void setNumeroSelo(Integer numeroSelo) {
		this.numeroSelo = numeroSelo;
	}

}
