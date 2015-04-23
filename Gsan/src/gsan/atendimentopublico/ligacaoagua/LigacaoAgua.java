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
package gsan.atendimentopublico.ligacaoagua;

import gsan.atendimentopublico.LigacaoOrigem;
import gsan.atendimentopublico.ordemservico.SupressaoMotivo;
import gsan.cadastro.imovel.Imovel;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class LigacaoAgua extends ObjetoTransacao {
	
	
	private static final long serialVersionUID = 1L;

	
	public static final int ATRIBUTOS_EFETUAR_LIGACAO = 257; // Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR
	public static final int ATRIBUTOS_ATUALIZAR_CONSUMO_MINIMO = 393; //Operacao.OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR
	public static final int ATRIBUTOS_EFETUAR_CORTE_LIGACAO_AGUA = 48; //Operacao.OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR
	public static final int ATRIBUTOS_EFETUAR_CORTE_ADMINISTRATIVO_LIGACAO_AGUA = 358;
		//	Operacao.OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR	
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO = 706;
			// Operacao.OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA = 882;
			
	public static final int ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO = 879;
		// Operacao.OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO
	public static final int ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA = 51;
		// Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR
	public static final int ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA = 685;
		// Operacao.OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR
	public static final int ATRIBUTOS_EFETUAR_RELIGACAO_AGUA = 50;
		// Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR
	public static final int ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA = 422;
		// Operacao.OPERACAO_ATUALIZAR_LIGACAO_AGUA
	public static final int ATRIBUTOS_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL = 1685; 
		//Operacao.OPERACAO_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL
	
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataImplantacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA)
	private Date dataLigacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA)	
	private Date dataSupressao;

	/** nullable persistent field */
	private Date dataCorte;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_RELIGACAO_AGUA, ATRIBUTOS_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL})	
	private Date dataReligacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA)	
	private Integer numeroSeloCorte;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA, ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})	
	private Integer numeroSeloSupressao;

	/** nullable persistent field */	
	private Date ultimaAlteracao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO)	
	private Date dataRestabelecimentoAgua;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_CONSUMO_MINIMO)	
	private Integer numeroConsumoMinimoAgua;

	/** nullable persistent field */
	private Short laguIcemissaocortesupressao;

	/** nullable persistent field */
	private Imovel imovel;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})	
	private gsan.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo;

	/** persistent field */
	private gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoAgua.LIGACAO_AGUA_PERFIL,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})		
	private gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil;

	/** persistent field */
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoAgua.LIGACAO_AGUA_DIAMETRO,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})	
	private gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoAgua.CORTE_TIPO,
			funcionalidade={ATRIBUTOS_EFETUAR_CORTE_LIGACAO_AGUA,ATRIBUTOS_EFETUAR_CORTE_ADMINISTRATIVO_LIGACAO_AGUA,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})		
	private gsan.atendimentopublico.ligacaoagua.CorteTipo corteTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoAgua.LIGACAO_AGUA_MATERIAL,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO,ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})	
	private gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial;

	/** nullable persistent field */
	@ControleAlteracao(value=FiltroLigacaoAgua.CORTE_MOTIVO,
			funcionalidade={ATRIBUTOS_EFETUAR_CORTE_LIGACAO_AGUA,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})		
	private gsan.atendimentopublico.ligacaoagua.MotivoCorte motivoCorte;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})	
	private SupressaoMotivo supressaoMotivo;

	/** nullable persistent field */
	private Date dataCorteAdministrativo;
	
	@ControleAlteracao(value=FiltroLigacaoAgua.RAMAL_LOCAL_LIGACAO,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO,ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})
	private RamalLocalInstalacao ramalLocalInstalacao;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO,
			ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA,
			ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})			
	private String numeroLacre;
	
	/** nullable persistent field */
	private LigacaoOrigem ligacaoOrigem;

	public LigacaoOrigem getLigacaoOrigem() {
		return ligacaoOrigem;
	}

	public void setLigacaoOrigem(LigacaoOrigem ligacaoOrigem) {
		this.ligacaoOrigem = ligacaoOrigem;
	}

	/**
	 * @return Retorna o campo numeroLacre.
	 */
	public String getNumeroLacre() {
		return numeroLacre;
	}

	/**
	 * @param numeroLacre O numeroLacre a ser setado.
	 */
	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}
	
	public Set medicaoHistoricos;
	
	public Set getMedicaoHistoricos() {
		return medicaoHistoricos;
	}

	public void setMedicaoHistoricos(Set medicaoHistoricos) {
		this.medicaoHistoricos = medicaoHistoricos;
	}

	/** full constructor */
	public LigacaoAgua(
			Date dataImplantacao,
			Date dataLigacao,
			Date dataSupressao,
			Date dataCorte,
			Date dataReligacao,
			Integer numeroSeloCorte,
			Integer numeroSeloSupressao,
			Date ultimaAlteracao,
			Date dataRestabelecimentoAgua,
			Date dataCorteAdministrativo,
			Integer numeroConsumoMinimoAgua,
			Short laguIcemissaocortesupressao,
			Imovel imovel,
			gsan.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo,
			gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro,
			gsan.atendimentopublico.ligacaoagua.CorteTipo corteTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial,
			gsan.atendimentopublico.ligacaoagua.MotivoCorte motivoCorte,
			SupressaoMotivo supressaoMotivo) {
		this.dataImplantacao = dataImplantacao;
		this.dataLigacao = dataLigacao;
		this.dataSupressao = dataSupressao;
		this.dataCorte = dataCorte;
		this.dataReligacao = dataReligacao;
		this.numeroSeloCorte = numeroSeloCorte;
		this.numeroSeloSupressao = numeroSeloSupressao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
		this.dataCorteAdministrativo = dataCorteAdministrativo;
		this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
		this.laguIcemissaocortesupressao = laguIcemissaocortesupressao;
		this.imovel = imovel;
		this.supressaoTipo = supressaoTipo;
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
		this.corteTipo = corteTipo;
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
		this.motivoCorte = motivoCorte;
		this.supressaoMotivo = supressaoMotivo;
	}

	/** default constructor */
	public LigacaoAgua() {
	}

	/** minimal constructor */
	public LigacaoAgua(
			gsan.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo,
			gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro,
			gsan.atendimentopublico.ligacaoagua.CorteTipo corteTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial) {
		this.supressaoTipo = supressaoTipo;
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
		this.corteTipo = corteTipo;
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
	}
	
	/** full constructor */
	public LigacaoAgua(Integer id,
			Date dataImplantacao,
			Date dataLigacao,
			Date dataSupressao,
			Date dataCorte,
			Date dataReligacao,
			Integer numeroSeloCorte,
			Integer numeroSeloSupressao,
			Date ultimaAlteracao,
			Date dataRestabelecimentoAgua,
			Date dataCorteAdministrativo,
			Integer numeroConsumoMinimoAgua,
			Short laguIcemissaocortesupressao,
			Imovel imovel,
			gsan.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo,
			gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro,
			gsan.atendimentopublico.ligacaoagua.CorteTipo corteTipo,
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial,
			gsan.atendimentopublico.ligacaoagua.MotivoCorte motivoCorte,
			SupressaoMotivo supressaoMotivo) {
		this.id = id;
		this.dataImplantacao = dataImplantacao;
		this.dataLigacao = dataLigacao;
		this.dataSupressao = dataSupressao;
		this.dataCorte = dataCorte;
		this.dataReligacao = dataReligacao;
		this.numeroSeloCorte = numeroSeloCorte;
		this.numeroSeloSupressao = numeroSeloSupressao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
		this.dataCorteAdministrativo = dataCorteAdministrativo;
		this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
		this.laguIcemissaocortesupressao = laguIcemissaocortesupressao;
		this.imovel = imovel;
		this.supressaoTipo = supressaoTipo;
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
		this.corteTipo = corteTipo;
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
		this.motivoCorte = motivoCorte;
		this.supressaoMotivo = supressaoMotivo;
	}

	public Date getDataCorteAdministrativo() {
		return dataCorteAdministrativo;
	}

	public void setDataCorteAdministrativo(Date dataCorteAdministrativo) {
		this.dataCorteAdministrativo = dataCorteAdministrativo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataImplantacao() {
		return this.dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public Date getDataLigacao() {
		return this.dataLigacao;
	}

	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Date getDataSupressao() {
		return this.dataSupressao;
	}

	public void setDataSupressao(Date dataSupressao) {
		this.dataSupressao = dataSupressao;
	}

	public Date getDataCorte() {
		return this.dataCorte;
	}

	public void setDataCorte(Date dataCorte) {
		this.dataCorte = dataCorte;
	}

	public Date getDataReligacao() {
		return this.dataReligacao;
	}

	public void setDataReligacao(Date dataReligacao) {
		this.dataReligacao = dataReligacao;
	}

	public Integer getNumeroSeloCorte() {
		return this.numeroSeloCorte;
	}

	public void setNumeroSeloCorte(Integer numeroSeloCorte) {
		this.numeroSeloCorte = numeroSeloCorte;
	}

	public Integer getNumeroSeloSupressao() {
		return this.numeroSeloSupressao;
	}

	public void setNumeroSeloSupressao(Integer numeroSeloSupressao) {
		this.numeroSeloSupressao = numeroSeloSupressao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getNumeroConsumoMinimoAgua() {
		return this.numeroConsumoMinimoAgua;
	}

	public void setNumeroConsumoMinimoAgua(Integer numeroConsumoMinimoAgua) {
		this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
	}

	public Short getLaguIcemissaocortesupressao() {
		return this.laguIcemissaocortesupressao;
	}

	public void setLaguIcemissaocortesupressao(Short laguIcemissaocortesupressao) {
		this.laguIcemissaocortesupressao = laguIcemissaocortesupressao;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public gsan.atendimentopublico.ligacaoagua.SupressaoTipo getSupressaoTipo() {
		return this.supressaoTipo;
	}

	public void setSupressaoTipo(
			gsan.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo) {
		this.supressaoTipo = supressaoTipo;
	}

	public gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo getEmissaoOrdemCobrancaTipo() {
		return this.emissaoOrdemCobrancaTipo;
	}

	public void setEmissaoOrdemCobrancaTipo(
			gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo) {
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
	}

	public gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return this.ligacaoAguaPerfil;
	}

	public void setLigacaoAguaPerfil(
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return this.hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro getLigacaoAguaDiametro() {
		return this.ligacaoAguaDiametro;
	}

	public void setLigacaoAguaDiametro(
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro) {
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
	}

	public gsan.atendimentopublico.ligacaoagua.CorteTipo getCorteTipo() {
		return this.corteTipo;
	}

	public void setCorteTipo(
			gsan.atendimentopublico.ligacaoagua.CorteTipo corteTipo) {
		this.corteTipo = corteTipo;
	}

	public gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial getLigacaoAguaMaterial() {
		return this.ligacaoAguaMaterial;
	}

	public void setLigacaoAguaMaterial(
			gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial) {
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
	}

	public gsan.atendimentopublico.ligacaoagua.MotivoCorte getMotivoCorte() {
		return this.motivoCorte;
	}

	public void setMotivoCorte(
			gsan.atendimentopublico.ligacaoagua.MotivoCorte motivoCorte) {
		this.motivoCorte = motivoCorte;
	}

	public SupressaoMotivo getSupressaoMotivo() {
		return this.supressaoMotivo;
	}

	public void setSupressaoMotivo(SupressaoMotivo supressaoMotivo) {
		this.supressaoMotivo = supressaoMotivo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();

		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("supressaoTipo");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("emissaoOrdemCobrancaTipo");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaPerfil");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaDiametro");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("corteTipo");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaMaterial");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("motivoCorte");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("supressaoMotivo");

		filtroLigacaoAgua.adicionarParametro(
			new ParametroSimples(FiltroLigacaoAgua.ID, 
				this.getId()));
		
		return filtroLigacaoAgua;
	}

	/**
	 * @return Retorna o campo dataRestabelecimentoAgua.
	 */
	public Date getDataRestabelecimentoAgua() {
		return dataRestabelecimentoAgua;
	}

	/**
	 * @param dataRestabelecimentoAgua
	 *            O dataRestabelecimentoAgua a ser setado.
	 */
	public void setDataRestabelecimentoAgua(Date dataRestabelecimentoAgua) {
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
	}

	public RamalLocalInstalacao getRamalLocalInstalacao() {
		return ramalLocalInstalacao;
	}

	public void setRamalLocalInstalacao(RamalLocalInstalacao ramalLocalInstalacao) {
		this.ramalLocalInstalacao = ramalLocalInstalacao;
	}
	
}
