/**
 * 
 */
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

package gcom.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Date;

public class ExecucaoOSFoto extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	public static final Integer FOTO_HIDROMETRO_SUBSTITUICAO = 9;

	private Integer id;
    private ArquivoTextoOSCobranca arquivoTextoOSCobranca;
    private OrdemServico ordemServico;
    private FotoSituacaoOrdemServico fotoSituacaoOrdemServico;
    private Date dataFoto;
	private String descricaoFoto;
	private byte[] fotoOrdemServico;
	private Date ultimaAlteracao;
	private String observacaoFoto;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	
	public ExecucaoOSFoto(){

	}
	
	public ExecucaoOSFoto( Integer idArquivo, Integer numeroOS, Integer tipoFoto, byte[] fotoOrdemServico ,BigDecimal coordenadaX, BigDecimal coordenadaY){
		
		ArquivoTextoOSCobranca atoc = new ArquivoTextoOSCobranca();
		atoc.setId( idArquivo );
		this.arquivoTextoOSCobranca = atoc;
		
		OrdemServico os = new OrdemServico();
		os.setId( numeroOS );
		this.ordemServico = os;
		
		FotoSituacaoOrdemServico fsos = new FotoSituacaoOrdemServico();
		fsos.setId( tipoFoto );
		this.fotoSituacaoOrdemServico = fsos;
						
		this.setDataFoto(new Date());
		this.setDescricaoFoto("");
		this.setFotoOrdemServico(fotoOrdemServico);
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.setUltimaAlteracao(new Date());		
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

	public FotoSituacaoOrdemServico getFotoSituacaoOrdemServico() {
		return fotoSituacaoOrdemServico;
	}

	public void setFotoSituacaoOrdemServico(FotoSituacaoOrdemServico fotoSituacaoOrdemServico) {
		this.fotoSituacaoOrdemServico = fotoSituacaoOrdemServico;
	}

	public Date getDataFoto() {
		return dataFoto;
	}
	public void setDataFoto(Date dataFoto) {
		this.dataFoto = dataFoto;
	}
	public String getDescricaoFoto() {
		return descricaoFoto;
	}
	public void setDescricaoFoto(String descricaoFoto) {
		this.descricaoFoto = descricaoFoto;
	}
	public byte[] getFoto() {
		return fotoOrdemServico;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public byte[] getFotoOrdemServico() {
		return fotoOrdemServico;
	}
	public void setFotoOrdemServico(byte[] fotoOrdemServico) {
		this.fotoOrdemServico = fotoOrdemServico;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public String getObservacaoFoto() {
		return observacaoFoto;
	}

	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(BigDecimal coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(BigDecimal coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public void setObservacaoFoto(String observacaoFoto) {
		this.observacaoFoto = observacaoFoto;
	}
	
	
}