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
package gcom.atualizacaocadastral;

import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DadosFinanceirosAtualizacaoCadastralDM implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer cadastrador;
	private Date dataGeracao;
	private Integer comando;
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer codSetorAntes;
	private Integer numQuadraAntes;
	private Integer loteAntes;
	private Integer subloteAntes;
	private Integer matricula;
	private String nomeClienteAntes;
	private Integer qtdeEconomiaResAntes;
	private Integer qtdeEconomiaComAntes;
	private Integer qtdeEconomiaIndAntes;
	private Integer qtdeEconomiaPubAntes;
	private Integer sitAguaAntes;
	private Integer sitEsgotoAntes;
	private String numRGAntes;
	private String numCPFCNPJAntes;
	private String numHidrometroAntes;
	private Integer codSetorDepois;
	private Integer numQuadraDepois;
	private Integer loteDepois;
	private Integer subloteDepois;
	private String nomeClienteDepois;
	private Integer qtdeEconomiaResDepois;
	private Integer qtdeEconomiaComDepois;
	private Integer qtdeEconomiaIndDepois;
	private Integer qtdeEconomiaPubDepois;
	private Integer sitAguaDepois;
	private Integer sitEsgotoDepois;
	private String numRGDepois;
	private String numCPFCNPJDepois;
	private String numHidrometroDepois;
	private Short icImovelFinalizado;
	private Date dataAlteracao;
	private Integer imacAntes;
	private Integer imacDepois;

	private Short alteracaoNome;
	private Short alteracaoEndereco;
	private Short alteracaoCategoria;
	private Short alteracaoSubcategoria;
	private Short alteracaoEconomia;
	private Short alteracaoSituacaoAgua;
	private Short alteracaoSituacaoEsgoto;
	private Short alteracaoHidrometro;
	private Short alteracaoInscricao;
	private Short rgIncluido;
	private Short rgAlterado;
	private Short cpfCnpjAlterado;
	private Short cpfCnpjIncluido;
	private Short cpfCnpjExcluido;

	private Integer consumoAgua;
	private Integer consumoEsgoto;
	private BigDecimal valorAguaAntes;
	private BigDecimal valorAguaDepois;
	private BigDecimal valorEsgotoAntes;
	private BigDecimal valorEsgotoDepois;
	private BigDecimal valorMulta;
	private BigDecimal valorConsumoFraudado;

	private Integer idLocalizacao;
	private Integer idSitAguaAntes;
	private Integer idSitAguaDepois;
	private Integer idSitEsgotoAntes;
	private Integer idSitEsgotoDepois;
	private Integer idTempo;
	private Integer idUsuario;
	private Integer idTarifa;
	//indicador de comando finalizado
	private Integer idIndicador;
	
	private String descLigAguaAntes;
	private String descLigAguaDepois;
	private String descLigEsgotoAntes;
	private String descLigEsgotoDepois;
	private String descTarifa;

	private Date ultimaAlteracao;
	
	public DadosFinanceirosAtualizacaoCadastralDM() { }

	public DadosFinanceirosAtualizacaoCadastralDM(Object[] array) {
		Short icAprovaCategoria = (Short) array[51];
		Short icAprovaEconomia = (Short) array[52];
		Short icAprovaSitAgua = (Short) array[53];
		Short icAprovaSitEsgoto = (Short) array[54];
		Short icAprovaCpfCnpj = (Short) array[55];
		Short icAprovaLogradouro = (Short) array[56];
		Short icAprovaInscricao = (Short) array[57];
		Short icAprovaHidrometro = (Short) array[58];
		Short icAprovaCliente = (Short) array[59];

		cadastrador = (Integer) array[0];
		dataGeracao = (Date) array[1];
		idGerenciaRegional = (Integer) array[2];
		idUnidadeNegocio = (Integer) array[3];
		idLocalidade = (Integer) array[4];
		codSetorAntes = (Integer) array[5];
		numQuadraAntes = (Integer) array[6];
		loteAntes = (Integer) array[61];
		subloteAntes = (Integer) array[62];
		matricula = (Integer) array[7];
		nomeClienteAntes = (String) array[8];
		qtdeEconomiaResAntes = (Integer) array[9];
		qtdeEconomiaComAntes = (Integer) array[10];
		qtdeEconomiaIndAntes = (Integer) array[11];
		qtdeEconomiaPubAntes = (Integer) array[12];
		sitAguaAntes = (Integer) array[13];
		sitEsgotoAntes = (Integer) array[14];
		Integer idLogradouroAntes = (Integer) array[15];
		Integer refEnderecoAntes = (Integer) array[16];
		String numImovelAntes = (String) array[17];
		numImovelAntes = (numImovelAntes != null && numImovelAntes.trim().equals("")) ? null : numImovelAntes;
		String complementoAntes = (String) array[18];
		complementoAntes = (complementoAntes != null && complementoAntes.trim().equals("")) ? null : complementoAntes;
		Integer idBairroAntes = (Integer) array[19];
		Integer codCEPAntes = (Integer) array[20];
		numRGAntes = (String) array[21];
		numRGAntes = (numRGAntes != null && numRGAntes.trim().equals("")) ? null : numRGAntes;
		numCPFCNPJAntes = (String) array[22];
		numCPFCNPJAntes = (numCPFCNPJAntes != null && numCPFCNPJAntes.trim().equals("")) ? null : numCPFCNPJAntes;
		numHidrometroAntes = (String) array[23];

		//Caso indicador de aprovado seja igual a 1(SIM) os campos recebem o valor do ambiente virtual 2, caso contrário recebe o mesmo valor da IDA.
		codSetorDepois = icAprovaInscricao.equals(ConstantesSistema.SIM) ? (Integer) array[24] : codSetorAntes;
		numQuadraDepois = icAprovaInscricao.equals(ConstantesSistema.SIM) ? (Integer) array[25] : numQuadraAntes;
		loteDepois = icAprovaInscricao.equals(ConstantesSistema.SIM) ? (Integer) array[63] : loteAntes;
		subloteDepois = icAprovaInscricao.equals(ConstantesSistema.SIM) ? (Integer) array[64] : subloteAntes;
		nomeClienteDepois = (icAprovaCpfCnpj.equals(ConstantesSistema.SIM) && icAprovaCliente.equals(ConstantesSistema.SIM)) ? (String) array[26] : nomeClienteAntes;
		qtdeEconomiaResDepois = (icAprovaCategoria.equals(ConstantesSistema.NAO) || icAprovaEconomia.equals(ConstantesSistema.NAO)) ? qtdeEconomiaResAntes : (Integer) array[27];
		qtdeEconomiaComDepois = (icAprovaCategoria.equals(ConstantesSistema.NAO) || icAprovaEconomia.equals(ConstantesSistema.NAO)) ? qtdeEconomiaComAntes : (Integer) array[28];
		qtdeEconomiaIndDepois = (icAprovaCategoria.equals(ConstantesSistema.NAO) || icAprovaEconomia.equals(ConstantesSistema.NAO)) ? qtdeEconomiaIndAntes : (Integer) array[29];
		qtdeEconomiaPubDepois = (icAprovaCategoria.equals(ConstantesSistema.NAO) || icAprovaEconomia.equals(ConstantesSistema.NAO)) ? qtdeEconomiaPubAntes : (Integer) array[30];
		sitAguaDepois = icAprovaSitAgua.equals(ConstantesSistema.SIM) ? (Integer) array[31] : sitAguaAntes;
		sitEsgotoDepois = icAprovaSitEsgoto.equals(ConstantesSistema.SIM) ? (Integer) array[32] : sitEsgotoAntes;
		Integer idLogradouroDepois = icAprovaLogradouro.equals(ConstantesSistema.SIM) ? (Integer) array[33] : idLogradouroAntes;
		Integer refEnderecoDepois = (Integer) array[34];
		String numImovelDepois = (String) array[35];
		numImovelDepois = (numImovelDepois != null && numImovelDepois.trim().equals("")) ? null : numImovelDepois;
		String complementoDepois = (String) array[36];
		complementoDepois = (complementoDepois != null && complementoDepois.trim().equals("")) ? null : complementoDepois;
		Integer idBairroDepois = icAprovaLogradouro.equals(ConstantesSistema.SIM) ? (Integer) array[37] : idBairroAntes;
		Integer codCEPDepois = icAprovaLogradouro.equals(ConstantesSistema.SIM) ? (Integer) array[38] : codCEPAntes;
		numRGDepois = (icAprovaCpfCnpj.equals(ConstantesSistema.SIM) && icAprovaCliente.equals(ConstantesSistema.SIM)) ? (String) array[39] : numRGAntes;
		numRGDepois = (numRGDepois != null && numRGDepois.trim().equals("")) ? null : numRGDepois;
		numCPFCNPJDepois = (icAprovaCpfCnpj.equals(ConstantesSistema.SIM) && icAprovaCliente.equals(ConstantesSistema.SIM)) ? (String) array[40] : numCPFCNPJAntes;
		numCPFCNPJDepois = (numCPFCNPJDepois != null && numCPFCNPJDepois.trim().equals("")) ? null : numCPFCNPJDepois;
		numHidrometroDepois = icAprovaHidrometro.equals(ConstantesSistema.SIM) ? (String) array[41] : numHidrometroAntes;
		icImovelFinalizado = (Short) array[42];
		
		Date dataAtualizacaoBatch = (Date) array[60];
		dataAlteracao = (Date) array[43];
		if(dataAlteracao == null){
			dataAlteracao = dataAtualizacaoBatch;
		}
		
		imacAntes = (Integer) array[44];
		imacDepois = (Integer) array[45];
		BigDecimal valorMultaAgua = (BigDecimal) array[47];
		BigDecimal valorConsumoFraudadoAgua = (BigDecimal) array[48];
		BigDecimal valorMultaEsgoto = (BigDecimal) array[49];
		BigDecimal valorConsumoFraudadoEsgoto = (BigDecimal) array[50];
		valorMulta = Util.somaBigDecimal(valorMultaAgua, valorMultaEsgoto);
		valorConsumoFraudado = Util.somaBigDecimal(valorConsumoFraudadoAgua, valorConsumoFraudadoEsgoto);

		if(codSetorAntes == null){
			//Imóvel novo
			alteracaoSubcategoria = ConstantesSistema.NAO;
			alteracaoNome = ConstantesSistema.NAO;
			alteracaoEndereco = ConstantesSistema.NAO;
			alteracaoCategoria = ConstantesSistema.NAO;
			alteracaoEconomia = ConstantesSistema.NAO;
			alteracaoSituacaoAgua = ConstantesSistema.NAO;
			alteracaoSituacaoEsgoto = ConstantesSistema.NAO;
			alteracaoHidrometro = ConstantesSistema.NAO;
			alteracaoInscricao = ConstantesSistema.NAO;
			rgIncluido = ConstantesSistema.NAO;
			rgAlterado = ConstantesSistema.NAO;
			cpfCnpjIncluido = ConstantesSistema.NAO;
			cpfCnpjExcluido = ConstantesSistema.NAO;
			cpfCnpjAlterado = ConstantesSistema.NAO;
			
		}else{
			
			Boolean icImovelResAntes = qtdeEconomiaResAntes > 0;
			Boolean icImovelComAntes = qtdeEconomiaComAntes > 0;
			Boolean icImovelIndAntes = qtdeEconomiaIndAntes > 0;
			Boolean icImovelPubAntes = qtdeEconomiaPubAntes > 0;
			Boolean icImovelResDepois = qtdeEconomiaResDepois > 0;
			Boolean icImovelComDepois = qtdeEconomiaComDepois > 0;
			Boolean icImovelIndDepois = qtdeEconomiaIndDepois > 0;
			Boolean icImovelPubDepois = qtdeEconomiaPubDepois > 0;
			
			alteracaoSubcategoria = icAprovaCategoria.equals(ConstantesSistema.SIM) ? ((Integer) array[46]).shortValue() : ConstantesSistema.NAO;

			alteracaoNome = comparar(nomeClienteAntes, nomeClienteDepois) ? ConstantesSistema.NAO : ConstantesSistema.SIM;

			alteracaoEndereco = (comparar(idLogradouroAntes, idLogradouroDepois) &&
					comparar(idBairroAntes, idBairroDepois) &&
					comparar(codCEPAntes, codCEPDepois) &&
					comparar(refEnderecoAntes, refEnderecoDepois) &&
					comparar(numImovelAntes, numImovelDepois) &&
					comparar(complementoAntes, complementoDepois)) ? ConstantesSistema.NAO : ConstantesSistema.SIM;
			
			alteracaoCategoria = (icAprovaCategoria.equals(ConstantesSistema.SIM) &&
					(!comparar(icImovelResAntes, icImovelResDepois) || !comparar(icImovelComAntes, icImovelComDepois) ||
					 !comparar(icImovelIndAntes, icImovelIndDepois) || !comparar(icImovelPubAntes, icImovelPubDepois))) ? ConstantesSistema.SIM : ConstantesSistema.NAO;
			
			Integer qtdeEconomiaAntes = qtdeEconomiaResAntes.intValue() + qtdeEconomiaComAntes.intValue() + qtdeEconomiaIndAntes.intValue() + qtdeEconomiaPubAntes.intValue();
			Integer qtdeEconomiaDepois = qtdeEconomiaResDepois.intValue() + qtdeEconomiaComDepois.intValue() + qtdeEconomiaIndDepois.intValue() + qtdeEconomiaPubDepois.intValue();
			alteracaoEconomia = comparar(qtdeEconomiaAntes, qtdeEconomiaDepois) ? ConstantesSistema.NAO : ConstantesSistema.SIM;
			
			alteracaoSituacaoAgua = comparar(sitAguaAntes, sitAguaDepois) ? ConstantesSistema.NAO : ConstantesSistema.SIM;
			alteracaoSituacaoEsgoto = comparar(sitEsgotoAntes, sitEsgotoDepois) ? ConstantesSistema.NAO : ConstantesSistema.SIM;
			alteracaoHidrometro = comparar(numHidrometroAntes, numHidrometroDepois) ? ConstantesSistema.NAO : ConstantesSistema.SIM;
			
			alteracaoInscricao = (comparar(codSetorAntes, codSetorDepois) &&
					comparar(numQuadraAntes, numQuadraDepois) &&
					comparar(loteAntes, loteDepois) &&
					comparar(subloteAntes, subloteDepois)) ? ConstantesSistema.NAO : ConstantesSistema.SIM;

			rgIncluido = (Util.verificarVazio(numRGAntes) && Util.verificarNaoVazio(numRGDepois)) ? ConstantesSistema.SIM : ConstantesSistema.NAO;
			rgAlterado = (rgIncluido == 1 || comparar(numRGAntes, numRGDepois)) ? ConstantesSistema.NAO : ConstantesSistema.SIM;

			cpfCnpjIncluido = (Util.verificarVazio(numCPFCNPJAntes) && Util.verificarNaoVazio(numCPFCNPJDepois)) ?	ConstantesSistema.SIM : ConstantesSistema.NAO;
			cpfCnpjExcluido = (Util.verificarNaoVazio(numCPFCNPJAntes) && Util.verificarVazio(numCPFCNPJDepois)) ?	ConstantesSistema.SIM : ConstantesSistema.NAO;
			cpfCnpjAlterado = (cpfCnpjIncluido == 1 || cpfCnpjExcluido == 1 || comparar(numCPFCNPJAntes, numCPFCNPJDepois)) ? ConstantesSistema.NAO : ConstantesSistema.SIM;
		}

		valorAguaAntes = BigDecimal.ZERO;
		valorAguaDepois = BigDecimal.ZERO;
		valorEsgotoAntes = BigDecimal.ZERO;
		valorEsgotoDepois = BigDecimal.ZERO;

		ultimaAlteracao = new Date();
	}

	private boolean comparar(Object obj1, Object obj2) {
		return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Integer cadastrador) {
		this.cadastrador = cadastrador;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getComando() {
		return comando;
	}

	public void setComando(Integer comando) {
		this.comando = comando;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getCodSetorAntes() {
		return codSetorAntes;
	}

	public void setCodSetorAntes(Integer codSetorAntes) {
		this.codSetorAntes = codSetorAntes;
	}

	public Integer getNumQuadraAntes() {
		return numQuadraAntes;
	}

	public void setNumQuadraAntes(Integer numQuadraAntes) {
		this.numQuadraAntes = numQuadraAntes;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNomeClienteAntes() {
		return nomeClienteAntes;
	}

	public void setNomeClienteAntes(String nomeClienteAntes) {
		this.nomeClienteAntes = nomeClienteAntes;
	}

	public Integer getQtdeEconomiaResAntes() {
		return qtdeEconomiaResAntes;
	}

	public void setQtdeEconomiaResAntes(Integer qtdeEconomiaResAntes) {
		this.qtdeEconomiaResAntes = qtdeEconomiaResAntes;
	}

	public Integer getQtdeEconomiaComAntes() {
		return qtdeEconomiaComAntes;
	}

	public void setQtdeEconomiaComAntes(Integer qtdeEconomiaComAntes) {
		this.qtdeEconomiaComAntes = qtdeEconomiaComAntes;
	}

	public Integer getQtdeEconomiaIndAntes() {
		return qtdeEconomiaIndAntes;
	}

	public void setQtdeEconomiaIndAntes(Integer qtdeEconomiaIndAntes) {
		this.qtdeEconomiaIndAntes = qtdeEconomiaIndAntes;
	}

	public Integer getQtdeEconomiaPubAntes() {
		return qtdeEconomiaPubAntes;
	}

	public void setQtdeEconomiaPubAntes(Integer qtdeEconomiaPubAntes) {
		this.qtdeEconomiaPubAntes = qtdeEconomiaPubAntes;
	}

	public Integer getSitAguaAntes() {
		return sitAguaAntes;
	}

	public void setSitAguaAntes(Integer sitAguaAntes) {
		this.sitAguaAntes = sitAguaAntes;
	}

	public Integer getSitEsgotoAntes() {
		return sitEsgotoAntes;
	}

	public void setSitEsgotoAntes(Integer sitEsgotoAntes) {
		this.sitEsgotoAntes = sitEsgotoAntes;
	}

	public String getNumCPFCNPJAntes() {
		return numCPFCNPJAntes;
	}

	public void setNumCPFCNPJAntes(String numCPFCNPJAntes) {
		this.numCPFCNPJAntes = numCPFCNPJAntes;
	}

	public String getNumHidrometroAntes() {
		return numHidrometroAntes;
	}

	public void setNumHidrometroAntes(String numHidrometroAntes) {
		this.numHidrometroAntes = numHidrometroAntes;
	}

	public String getNumCPFCNPJDepois() {
		return numCPFCNPJDepois;
	}

	public void setNumCPFCNPJDepois(String numCPFCNPJDepois) {
		this.numCPFCNPJDepois = numCPFCNPJDepois;
	}

	public Integer getCodSetorDepois() {
		return codSetorDepois;
	}

	public void setCodSetorDepois(Integer codSetorDepois) {
		this.codSetorDepois = codSetorDepois;
	}

	public Integer getNumQuadraDepois() {
		return numQuadraDepois;
	}

	public void setNumQuadraDepois(Integer numQuadraDepois) {
		this.numQuadraDepois = numQuadraDepois;
	}

	public String getNomeClienteDepois() {
		return nomeClienteDepois;
	}

	public void setNomeClienteDepois(String nomeClienteDepois) {
		this.nomeClienteDepois = nomeClienteDepois;
	}

	public Integer getQtdeEconomiaResDepois() {
		return qtdeEconomiaResDepois;
	}

	public void setQtdeEconomiaResDepois(Integer qtdeEconomiaResDepois) {
		this.qtdeEconomiaResDepois = qtdeEconomiaResDepois;
	}

	public Integer getQtdeEconomiaComDepois() {
		return qtdeEconomiaComDepois;
	}

	public void setQtdeEconomiaComDepois(Integer qtdeEconomiaComDepois) {
		this.qtdeEconomiaComDepois = qtdeEconomiaComDepois;
	}

	public Integer getQtdeEconomiaIndDepois() {
		return qtdeEconomiaIndDepois;
	}

	public void setQtdeEconomiaIndDepois(Integer qtdeEconomiaIndDepois) {
		this.qtdeEconomiaIndDepois = qtdeEconomiaIndDepois;
	}

	public Integer getQtdeEconomiaPubDepois() {
		return qtdeEconomiaPubDepois;
	}

	public void setQtdeEconomiaPubDepois(Integer qtdeEconomiaPubDepois) {
		this.qtdeEconomiaPubDepois = qtdeEconomiaPubDepois;
	}

	public Integer getSitAguaDepois() {
		return sitAguaDepois;
	}

	public void setSitAguaDepois(Integer sitAguaDepois) {
		this.sitAguaDepois = sitAguaDepois;
	}

	public Integer getSitEsgotoDepois() {
		return sitEsgotoDepois;
	}

	public void setSitEsgotoDepois(Integer sitEsgotoDepois) {
		this.sitEsgotoDepois = sitEsgotoDepois;
	}

	public String getNumRGAntes() {
		return numRGAntes;
	}

	public void setNumRGAntes(String numRGAntes) {
		this.numRGAntes = numRGAntes;
	}

	public String getNumRGDepois() {
		return numRGDepois;
	}

	public void setNumRGDepois(String numRGDepois) {
		this.numRGDepois = numRGDepois;
	}

	public String getNumHidrometroDepois() {
		return numHidrometroDepois;
	}

	public void setNumHidrometroDepois(String numHidrometroDepois) {
		this.numHidrometroDepois = numHidrometroDepois;
	}

	public Short getIcImovelFinalizado() {
		return icImovelFinalizado;
	}

	public void setIcImovelFinalizado(Short indImovelFinalizado) {
		this.icImovelFinalizado = indImovelFinalizado;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Short getAlteracaoNome() {
		return alteracaoNome;
	}

	public void setAlteracaoNome(Short alteracaoNome) {
		this.alteracaoNome = alteracaoNome;
	}

	public Short getAlteracaoEndereco() {
		return alteracaoEndereco;
	}

	public void setAlteracaoEndereco(Short alteracaoEndereco) {
		this.alteracaoEndereco = alteracaoEndereco;
	}

	public Short getAlteracaoCategoria() {
		return alteracaoCategoria;
	}

	public void setAlteracaoCategoria(Short alteracaoCategoria) {
		this.alteracaoCategoria = alteracaoCategoria;
	}

	public Short getAlteracaoSubcategoria() {
		return alteracaoSubcategoria;
	}

	public void setAlteracaoSubcategoria(Short alteracaoSubcategoria) {
		this.alteracaoSubcategoria = alteracaoSubcategoria;
	}

	public Short getAlteracaoEconomia() {
		return alteracaoEconomia;
	}

	public void setAlteracaoEconomia(Short alteracaoEconomia) {
		this.alteracaoEconomia = alteracaoEconomia;
	}

	public Short getAlteracaoSituacaoAgua() {
		return alteracaoSituacaoAgua;
	}

	public void setAlteracaoSituacaoAgua(Short alteracaoSituacaoAgua) {
		this.alteracaoSituacaoAgua = alteracaoSituacaoAgua;
	}

	public Short getAlteracaoSituacaoEsgoto() {
		return alteracaoSituacaoEsgoto;
	}

	public void setAlteracaoSituacaoEsgoto(Short alteracaoSituacaoEsgoto) {
		this.alteracaoSituacaoEsgoto = alteracaoSituacaoEsgoto;
	}

	public Short getRgIncluido() {
		return rgIncluido;
	}

	public void setRgIncluido(Short rgIncluido) {
		this.rgIncluido = rgIncluido;
	}

	public Short getRgAlterado() {
		return rgAlterado;
	}

	public void setRgAlterado(Short rgAlterado) {
		this.rgAlterado = rgAlterado;
	}

	public Short getCpfCnpjAlterado() {
		return cpfCnpjAlterado;
	}

	public void setCpfCnpjAlterado(Short cpfCnpjAlterado) {
		this.cpfCnpjAlterado = cpfCnpjAlterado;
	}

	public Short getCpfCnpjIncluido() {
		return cpfCnpjIncluido;
	}

	public void setCpfCnpjIncluido(Short cpfCnpjIncluido) {
		this.cpfCnpjIncluido = cpfCnpjIncluido;
	}

	public Short getCpfCnpjExcluido() {
		return cpfCnpjExcluido;
	}

	public void setCpfCnpjExcluido(Short cpfCnpjExcluido) {
		this.cpfCnpjExcluido = cpfCnpjExcluido;
	}

	public Integer getImacAntes() {
		return imacAntes;
	}

	public void setImacAntes(Integer imacAntes) {
		this.imacAntes = imacAntes;
	}

	public Integer getImacDepois() {
		return imacDepois;
	}

	public void setImacDepois(Integer imacDepois) {
		this.imacDepois = imacDepois;
	}

	public BigDecimal getValorAguaAntes() {
		return valorAguaAntes;
	}

	public void setValorAguaAntes(BigDecimal valorAguaAntes) {
		this.valorAguaAntes = valorAguaAntes;
	}

	public BigDecimal getValorAguaDepois() {
		return valorAguaDepois;
	}

	public void setValorAguaDepois(BigDecimal valorAguaDepois) {
		this.valorAguaDepois = valorAguaDepois;
	}

	public BigDecimal getValorEsgotoAntes() {
		return valorEsgotoAntes;
	}

	public void setValorEsgotoAntes(BigDecimal valorEsgotoAntes) {
		this.valorEsgotoAntes = valorEsgotoAntes;
	}

	public BigDecimal getValorEsgotoDepois() {
		return valorEsgotoDepois;
	}

	public void setValorEsgotoDepois(BigDecimal valorEsgotoDepois) {
		this.valorEsgotoDepois = valorEsgotoDepois;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorConsumoFraudado() {
		return valorConsumoFraudado;
	}

	public void setValorConsumoFraudado(BigDecimal valorConsumoFraudado) {
		this.valorConsumoFraudado = valorConsumoFraudado;
	}

	public Integer getIdLocalizacao() {
		return idLocalizacao;
	}

	public void setIdLocalizacao(Integer idLocalizacao) {
		this.idLocalizacao = idLocalizacao;
	}

	public Short getAlteracaoHidrometro() {
		return alteracaoHidrometro;
	}

	public void setAlteracaoHidrometro(Short alteracaoHidrometro) {
		this.alteracaoHidrometro = alteracaoHidrometro;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getIdSitAguaAntes() {
		return idSitAguaAntes;
	}

	public void setIdSitAguaAntes(Integer idSitAguaAntes) {
		this.idSitAguaAntes = idSitAguaAntes;
	}

	public Integer getIdSitAguaDepois() {
		return idSitAguaDepois;
	}

	public void setIdSitAguaDepois(Integer idSitAguaDepois) {
		this.idSitAguaDepois = idSitAguaDepois;
	}

	public Integer getIdSitEsgotoAntes() {
		return idSitEsgotoAntes;
	}

	public void setIdSitEsgotoAntes(Integer idSitEsgotoAntes) {
		this.idSitEsgotoAntes = idSitEsgotoAntes;
	}

	public Integer getIdSitEsgotoDepois() {
		return idSitEsgotoDepois;
	}

	public void setIdSitEsgotoDepois(Integer idSitEsgotoDepois) {
		this.idSitEsgotoDepois = idSitEsgotoDepois;
	}

	public Integer getIdTempo() {
		return idTempo;
	}

	public void setIdTempo(Integer idTempo) {
		this.idTempo = idTempo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public Integer getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(Integer idTarifa) {
		this.idTarifa = idTarifa;
	}

	public String getDescLigAguaAntes() {
		return descLigAguaAntes;
	}

	public void setDescLigAguaAntes(String descLigAguaAntes) {
		this.descLigAguaAntes = descLigAguaAntes;
	}

	public String getDescLigAguaDepois() {
		return descLigAguaDepois;
	}

	public void setDescLigAguaDepois(String descLigAguaDepois) {
		this.descLigAguaDepois = descLigAguaDepois;
	}

	public String getDescLigEsgotoAntes() {
		return descLigEsgotoAntes;
	}

	public void setDescLigEsgotoAntes(String descLigEsgotoAntes) {
		this.descLigEsgotoAntes = descLigEsgotoAntes;
	}

	public String getDescLigEsgotoDepois() {
		return descLigEsgotoDepois;
	}

	public void setDescLigEsgotoDepois(String descLigEsgotoDepois) {
		this.descLigEsgotoDepois = descLigEsgotoDepois;
	}

	public String getDescTarifa() {
		return descTarifa;
	}

	public void setDescTarifa(String descTarifa) {
		this.descTarifa = descTarifa;
	}

	public Integer getLoteAntes() {
		return loteAntes;
	}

	public void setLoteAntes(Integer loteAntes) {
		this.loteAntes = loteAntes;
	}

	public Integer getSubloteAntes() {
		return subloteAntes;
	}

	public void setSubloteAntes(Integer subloteAntes) {
		this.subloteAntes = subloteAntes;
	}

	public Integer getLoteDepois() {
		return loteDepois;
	}

	public void setLoteDepois(Integer loteDepois) {
		this.loteDepois = loteDepois;
	}

	public Integer getSubloteDepois() {
		return subloteDepois;
	}

	public void setSubloteDepois(Integer subloteDepois) {
		this.subloteDepois = subloteDepois;
	}

	public Short getAlteracaoInscricao() {
		return alteracaoInscricao;
	}

	public void setAlteracaoInscricao(Short alteracaoInscricao) {
		this.alteracaoInscricao = alteracaoInscricao;
	}

	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
	}

}
