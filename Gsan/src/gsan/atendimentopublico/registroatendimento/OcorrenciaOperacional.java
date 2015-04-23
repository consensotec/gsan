package gsan.atendimentopublico.registroatendimento;

import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.localidade.Localidade;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;

import java.util.Date;

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
* Rômulo Aurélio de Melo Souza Filho
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
public class OcorrenciaOperacional extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** identifier field */
    private String descricao;

    /** identifier field */
    private String areasAfetadas;
    
    /** identifier field */
    private Date dataPrevisao;
    
    /** identifier field */
    private Date dataReprogramacao;

    /** identifier field */
    private Date dataConclusao;
    
    private Short codigoPeriodoPrevisao;
    
    private Short codigoPeriodoReprogramacao;
    
    private Short codigoPeriodoConclusao;
    
    /** identifier field */
    private String observacao;
    
    private Date dataOcorrencia;

    /** identifier field */
    private Date ultimaAlteracao;
    
    private Municipio municipio;
    
    private Bairro bairro;
    
    private Localidade localidade;
    
    private OcorrenciaOperacionalTipo ocorrenciaOperacionalTipo;
    
    private OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivo;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAreasAfetadas() {
		return areasAfetadas;
	}

	public void setAreasAfetadas(String areasAfetadas) {
		this.areasAfetadas = areasAfetadas;
	}

	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public Date getDataReprogramacao() {
		return dataReprogramacao;
	}

	public void setDataReprogramacao(Date dataReprogramacao) {
		this.dataReprogramacao = dataReprogramacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Short getCodigoPeriodoPrevisao() {
		return codigoPeriodoPrevisao;
	}

	public void setCodigoPeriodoPrevisao(Short codigoPeriodoPrevisao) {
		this.codigoPeriodoPrevisao = codigoPeriodoPrevisao;
	}

	public Short getCodigoPeriodoReprogramacao() {
		return codigoPeriodoReprogramacao;
	}

	public void setCodigoPeriodoReprogramacao(Short codigoPeriodoReprogramacao) {
		this.codigoPeriodoReprogramacao = codigoPeriodoReprogramacao;
	}

	public Short getCodigoPeriodoConclusao() {
		return codigoPeriodoConclusao;
	}

	public void setCodigoPeriodoConclusao(Short codigoPeriodoConclusao) {
		this.codigoPeriodoConclusao = codigoPeriodoConclusao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	
	public OcorrenciaOperacionalTipo getOcorrenciaOperacionalTipo() {
		return ocorrenciaOperacionalTipo;
	}

	public void setOcorrenciaOperacionalTipo(OcorrenciaOperacionalTipo ocorrenciaOperacionalTipo) {
		this.ocorrenciaOperacionalTipo = ocorrenciaOperacionalTipo;
	}

	public OcorrenciaOperacionalMotivo getOcorrenciaOperacionalMotivo() {
		return ocorrenciaOperacionalMotivo;
	}

	public void setOcorrenciaOperacionalMotivo(OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivo) {
		this.ocorrenciaOperacionalMotivo = ocorrenciaOperacionalMotivo;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    

}
