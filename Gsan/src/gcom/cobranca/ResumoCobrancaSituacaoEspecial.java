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
package gcom.cobranca;

import java.util.Date;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.micromedicao.Rota;

/**
 * 
 * Classe B�sica 
 *
 * @author Thiago Toscano
 * @date 15/05/2006
 */
public class ResumoCobrancaSituacaoEspecial {

	private Integer id;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer anoMesInicioSituacaoEspecial;
	private Integer anoMesFinalSituacaoEspecial;
	private Integer quantidadeImovel;
	private Date ultimaAlteracao;

	private GerenciaRegional gerenciaRegional;
	private Localidade localidade;
	private SetorComercial setorComercial;
	private Rota rota;
	private Quadra quadra;
	private ImovelPerfil imovelPerfil;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private Categoria categoria;
	private EsferaPoder esferaPoder;
	private CobrancaSituacaoTipo cobrancaSituacaoTipo;
	private CobrancaSituacaoMotivo cobrancaSituacaoMotivo;

	public ResumoCobrancaSituacaoEspecial (Integer codigoSetorComercial, Integer numeroQuadra, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer quantidadeImovel, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, CobrancaSituacaoTipo cobrancaSituacaoTipo, CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.quantidadeImovel = quantidadeImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	public ResumoCobrancaSituacaoEspecial (Integer codigoSetorComercial, Integer numeroQuadra, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer quantidadeImovel, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, CobrancaSituacaoTipo cobrancaSituacaoTipo, CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.quantidadeImovel = quantidadeImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ResumoCobrancaSituacaoEspecial (Integer id, Integer codigoSetorComercial, Integer numeroQuadra, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer quantidadeImovel, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, CobrancaSituacaoTipo cobrancaSituacaoTipo, CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.id = id;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.quantidadeImovel = quantidadeImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	public ResumoCobrancaSituacaoEspecial() {

	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo anoMesFinalSituacaoEspecial.
	 */
	public Integer getAnoMesFinalSituacaoEspecial() {
		return anoMesFinalSituacaoEspecial;
	}
	/**
	 * @param anoMesFinalSituacaoEspecial O anoMesFinalSituacaoEspecial a ser setado.
	 */
	public void setAnoMesFinalSituacaoEspecial(Integer anoMesFinalSituacaoEspecial) {
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
	}
	/**
	 * @return Retorna o campo anoMesInicioSituacaoEspecial.
	 */
	public Integer getAnoMesInicioSituacaoEspecial() {
		return anoMesInicioSituacaoEspecial;
	}
	/**
	 * @param anoMesInicioSituacaoEspecial O anoMesInicioSituacaoEspecial a ser setado.
	 */
	public void setAnoMesInicioSituacaoEspecial(Integer anoMesInicioSituacaoEspecial) {
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}
	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	/**
	 * @return Retorna o campo cobrancaSituacaoMotivo.
	 */
	public CobrancaSituacaoMotivo getCobrancaSituacaoMotivo() {
		return cobrancaSituacaoMotivo;
	}

	/**
	 * @param cobrancaSituacaoMotivo O cobrancaSituacaoMotivo a ser setado.
	 */
	public void setCobrancaSituacaoMotivo(
			CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	/**
	 * @return Retorna o campo cobrancaSituacaoTipo.
	 */
	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}

	/**
	 * @param cobrancaSituacaoTipo O cobrancaSituacaoTipo a ser setado.
	 */
	public void setCobrancaSituacaoTipo(CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}
	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}
	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}
	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}
	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}
	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}
	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}
	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}
	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
