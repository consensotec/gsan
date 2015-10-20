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
package gcom.atualizacaocadastral.bean;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * [UC1447] - Consultar Imóveis no Ambiente Pré-GSAN
 * 
 * @author Bruno Sá Barreto
 * @created 23/09/2014
 */
public class DadosImovelPreGsanHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//tipos de seleção
	public static final short TIPO_SELECAO_IMOVEL_NOVO = 1;
	public static final short TIPO_SELECAO_COM_OCORRENCIA_CADASTRO = 2;
	
	//ações
	public static final String ACAO_LIBERAR_PARA_ATUALIZACAO = "Liberado para Atualização GSAN";
	public static final String ACAO_RETORNAR_PARA_CAMPO = "Retornar para Campo";
	public static final String ACAO_REMOVER_REGISTRO_ATLZ_CADASTRAL = "Remover Registro Atualização Cadastral";
	public static final String ACAO_REMOVER_MATRICULA_INDICADA = "Remover Matrícula Indicada";
	
	//situações
	public static final short SITUACAO_LOGRADOURO_NAO_INFORMADO = 1;
	public static final short SITUACAO_SETOR_COMERCIAL_INEXISTENTE = 2;
	public static final short SITUACAO_QUADRA_INEXISTENTE = 3;
	public static final short SITUACAO_MATRICULA_DUPLICADA_AMB_VIRTUAL_2 = 4;
	public static final short SITUACAO_MATRICULA_DUPLICADA_GSAN = 5;
		
	//Atributos utilizados na exibição da lista de resultados
	private ArrayList<String> acoesDisponiveis;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String numeroLote;
	private String enderecoFormatado;
	private String matricula;
	private String matriculaGsan;
	private String descricaoCadastroOcorrencia;
	private String numeroVisitas;
	
	//Atributos usados para exibir na tela o conteudo dos imóveis pesquisados
	private String numeroSubLote;
	private String idImovelAtualizacaoCadastral;
	private String situacao;
	private String idLocalidade;
	private String numero;
	private String indicadorHabilitaTipoSituacao;
	private String indicadorValueSelect;
	private String indicadorImovelNovo;
	private String idComando;
	private String loginCadastrador;
	private String nomeCadastrador;
	private String idCadastroOcorrencia;
	private String qtdImoveis;
	private String totalImovelPorCadastrador;
	private String totalImovel;
	private String dadosMatriculaAssociadaHint;
	@SuppressWarnings("rawtypes")
	private Collection colecaoMatriculaGsan;
	private String nomeCliente;
	private String cpfCnpj;
	private String idLogradouro;
	
	//Atributos que indicam a situação do imóvel
	private String indicadorLogradouroNaoInformado; //1
	private String indicadorSetorComercialInexistente; //2
	private String indicadorQuadraInexistente; //3
	private String indicadorInscrDuplicadaAmbVirtual2; //4
	private String indicadorInscrDuplicadaGSAN; //5
	private String indicadoresSituacao = "";
	
	//Atributos usados como parametros para consultar os imóveis na base de dados
	private String parametroEmpresa;
	private String parametroLocalidade;
	private String parametroSetorComercial;
	private String parametroQuadra;
	private String parametroCadastroOcorrencia;
	private String parametroTipoSelecao;
	private String parametroCadastrador;

	public void setIndicadoresSituacao(String indicadoresSituacao) {
		this.indicadoresSituacao = indicadoresSituacao;
	}

	public void setAcoesDisponiveis(ArrayList<String> acoesDisponiveis) {
		
		this.acoesDisponiveis = acoesDisponiveis;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public String getIndicadorInscrDuplicadaAmbVirtual2() {
		return indicadorInscrDuplicadaAmbVirtual2;
	}

	public void setIndicadorInscrDuplicadaAmbVirtual2(
			String indicadorInscrDuplicadaAmbVirtual2) {
		this.indicadorInscrDuplicadaAmbVirtual2 = indicadorInscrDuplicadaAmbVirtual2;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMatriculaGsan() {
		return matriculaGsan;
	}

	public void setMatriculaGsan(String matriculaGsan) {
		this.matriculaGsan = matriculaGsan;
	}

	public String getDescricaoCadastroOcorrencia() {
		return descricaoCadastroOcorrencia;
	}

	public void setDescricaoCadastroOcorrencia(String descricaoCadastroOcorrencia) {
		this.descricaoCadastroOcorrencia = descricaoCadastroOcorrencia;
	}

	public String getNumeroVisitas() {
		return numeroVisitas;
	}

	public void setNumeroVisitas(String numeroVisitas) {
		this.numeroVisitas = numeroVisitas;
	}

	public String getIdImovelAtualizacaoCadastral() {
		return idImovelAtualizacaoCadastral;
	}

	public void setIdImovelAtualizacaoCadastral(String idImovelAtualizacaoCadastral) {
		this.idImovelAtualizacaoCadastral = idImovelAtualizacaoCadastral;
	}

	public String getParametroEmpresa() {
		return parametroEmpresa;
	}

	public void setParametroEmpresa(String parametroEmpresa) {
		this.parametroEmpresa = parametroEmpresa;
	}

	public String getParametroLocalidade() {
		return parametroLocalidade;
	}

	public void setParametroLocalidade(String parametroLocalidade) {
		this.parametroLocalidade = parametroLocalidade;
	}

	public String getParametroSetorComercial() {
		return parametroSetorComercial;
	}

	public void setParametroSetorComercial(String parametroSetorComercial) {
		this.parametroSetorComercial = parametroSetorComercial;
	}

	public String getParametroQuadra() {
		return parametroQuadra;
	}

	public void setParametroQuadra(Integer[] parametroQuadra) {
		String retorno = "";

		if ( parametroQuadra != null && parametroQuadra.length > 0 ) {
			for (int i = 0; i < parametroQuadra.length; i++) {
				retorno += parametroQuadra[i] + ",";
			}
			retorno = retorno.substring(0, retorno.length() -1);
		}
		
		this.parametroQuadra = retorno;
	}

	public String getParametroCadastroOcorrencia() {
		return parametroCadastroOcorrencia;
	}

	public void setParametroCadastroOcorrencia(String parametroCadastroOcorrencia) {
		this.parametroCadastroOcorrencia = parametroCadastroOcorrencia;
	}

	public String getParametroTipoSelecao() {
		return parametroTipoSelecao;
	}

	public void setParametroTipoSelecao(String parametroTipoSelecao) {
		this.parametroTipoSelecao = parametroTipoSelecao;
	}

	public String getIndicadorLogradouroNaoInformado() {
		return indicadorLogradouroNaoInformado;
	}

	public void setIndicadorLogradouroNaoInformado(
			String indicadorLogradouroNaoInformado) {
		this.indicadorLogradouroNaoInformado = indicadorLogradouroNaoInformado;
	}

	public String getIndicadorHabilitaTipoSituacao() {
		return indicadorHabilitaTipoSituacao;
	}

	public void setIndicadorHabilitaTipoSituacao(
			String indicadorHabilitaTipoSituacao) {
		this.indicadorHabilitaTipoSituacao = indicadorHabilitaTipoSituacao;
	}

	public String getIndicadorImovelNovo() {
		return indicadorImovelNovo;
	}

	public void setIndicadorImovelNovo(String indicadorImovelNovo) {
		this.indicadorImovelNovo = indicadorImovelNovo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(String numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public String getIndicadorValueSelect() {
		return indicadorValueSelect;
	}

	public void setIndicadorValueSelect(String indicadorValueSelect) {
		this.indicadorValueSelect = indicadorValueSelect;
	}

	@SuppressWarnings("rawtypes")
	public Collection getColecaoMatriculaGsan() {
		return colecaoMatriculaGsan;
	}

	@SuppressWarnings("rawtypes")
	public void setColecaoMatriculaGsan(Collection colecaoMatriculaGsan) {
		this.colecaoMatriculaGsan = colecaoMatriculaGsan;
	}

	public String getIndicadorSetorComercialInexistente() {
		return indicadorSetorComercialInexistente;
	}

	public void setIndicadorSetorComercialInexistente(
			String indicadorSetorComercialInexistente) {
		this.indicadorSetorComercialInexistente = indicadorSetorComercialInexistente;
	}

	public String getIndicadorQuadraInexistente() {
		return indicadorQuadraInexistente;
	}

	public void setIndicadorQuadraInexistente(String indicadorQuadraInexistente) {
		this.indicadorQuadraInexistente = indicadorQuadraInexistente;
	}

	public String getIdComando() {
		return idComando;
	}

	public void setIdComando(String idComando) {
		this.idComando = idComando;
	}
	
	public String getLoginCadastrador() {
		return loginCadastrador;
	}

	public void setLoginCadastrador(String loginCadastrador) {
		this.loginCadastrador = loginCadastrador;
	}

	public String getNomeCadastrador() {
		return nomeCadastrador;
	}

	public void setNomeCadastrador(String nomeCadastrador) {
		this.nomeCadastrador = nomeCadastrador;
	}

	public String getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}

	public void setIdCadastroOcorrencia(String idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}
	
	public String getQtdImoveis() {
		return qtdImoveis;
	}
	
	public void setQtdImoveis(String qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}	
	
	public String getTotalImovelPorCadastrador() {
		return totalImovelPorCadastrador;
	}

	public void setTotalImovelPorCadastrador(String totalImovelPorCadastrador) {
		this.totalImovelPorCadastrador = totalImovelPorCadastrador;
	}

	public String getTotalImovel() {
		return totalImovel;
	}

	public void setTotalImovel(String totalImovel) {
		this.totalImovel = totalImovel;
	}

	public void setDadosMatriculaAssociadaHint(String dadosMatriculaAssociadaHint) {
		this.dadosMatriculaAssociadaHint = dadosMatriculaAssociadaHint;
	}

	public String getParametroCadastrador() {
		return parametroCadastrador;
	}

	public void setParametroCadastrador(String parametroCadastrador) {
		this.parametroCadastrador = parametroCadastrador;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
//	Início dos métodos auxiliares
	
	public String getInscricaoFormatada(){
		String inscricao = this.getIdLocalidade()+"."+ this.getCodigoSetorComercial() +"."+ this.getNumeroQuadra() +"."+this.getNumeroLote() +"."+ this.getNumeroSubLote();
		return inscricao;
	}
	
	public String getDadosMatriculaAssociadaHint() {
        dadosMatriculaAssociadaHint = "";
        
        String matricula = getMatricula();
        
        if ( matricula != null && !matricula.equals( "" ) ){
            Fachada fachada = Fachada.getInstancia();
            
            String inscricao = fachada.pesquisarInscricaoImovelExcluidoOuNao( Integer.parseInt( matricula ) );
             
            Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( Integer.parseInt( matricula ) );
            
            FiltroImovel filtro = new FiltroImovel();
            filtro.adicionarParametro( new ParametroSimples( FiltroImovel.ID, matricula ) );
            filtro.adicionarCaminhoParaCarregamentoEntidade( "ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro" );
            @SuppressWarnings("unchecked")
			Collection<Imovel> colImovel = fachada.pesquisar( filtro, Imovel.class.getName() );
            
            Imovel imovel = ( Imovel ) colImovel.iterator().next();
            
            String numeroHidrometro = 
                  imovel.getLigacaoAgua() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() != null ? 
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() : "-" ;
            
            String usuario = "";
            
            if (clienteUsuario != null) {
            	usuario = clienteUsuario.getCodigoNome();
            }
            
    		try {
                dadosMatriculaAssociadaHint =  "Inscrição: " + 
                         inscricao + "<br>" +
                         "Cliente Usuário: " +
                         usuario + "<br>" +
                         "Hidrômetro: " +
                         numeroHidrometro + "<br>" +                     
                         "Endereço: " +
                         fachada.pesquisarEndereco( Integer.parseInt( matricula ) ) + "<br>";        
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        return dadosMatriculaAssociadaHint.replace( '\n', ' ' );
	}

	public ArrayList<String> getAcoesDisponiveis() {
		
		acoesDisponiveis = new ArrayList<String>();
		
		if(getIndicadoresSituacao().length()==0){
			acoesDisponiveis.add(ACAO_LIBERAR_PARA_ATUALIZACAO);
		}
		
		if(getIndicadorImovelNovo().equals(String.valueOf(ConstantesSistema.NAO)) && Integer.valueOf(getNumeroVisitas())<3){			
			acoesDisponiveis.add(ACAO_RETORNAR_PARA_CAMPO);
		}
		
		if(getIndicadorImovelNovo().equals(String.valueOf(ConstantesSistema.SIM)) && (getMatriculaGsan()!=null&&!getMatriculaGsan().trim().equals("")) && getIndicadoresSituacao().length()==0){
			acoesDisponiveis.add(ACAO_REMOVER_MATRICULA_INDICADA);
		}
		
		acoesDisponiveis.add(ACAO_REMOVER_REGISTRO_ATLZ_CADASTRAL);
		
		return acoesDisponiveis;
	}

	public String getIndicadoresSituacao() { 

		indicadoresSituacao = "";
		if (Util.verificarNaoVazio(indicadorLogradouroNaoInformado)) {
			if (indicadorLogradouroNaoInformado.equals(String
					.valueOf(ConstantesSistema.SIM))) {
				indicadoresSituacao += SITUACAO_LOGRADOURO_NAO_INFORMADO;
			}
		}
		if (Util.verificarNaoVazio(indicadorSetorComercialInexistente)) {
			if (indicadorSetorComercialInexistente.equals(String
					.valueOf(ConstantesSistema.SIM))) {
				indicadoresSituacao += indicadoresSituacao.length() > 0 ? ", " + SITUACAO_SETOR_COMERCIAL_INEXISTENTE
						: "2";
			}
		}
		if (Util.verificarNaoVazio(indicadorQuadraInexistente)) {
			if (indicadorQuadraInexistente.equals(String
					.valueOf(ConstantesSistema.SIM))) {
				indicadoresSituacao += indicadoresSituacao.length() > 0 ? ", " + SITUACAO_QUADRA_INEXISTENTE
						: "3";
			}
		}
		if (Util.verificarNaoVazio(indicadorInscrDuplicadaAmbVirtual2)) {
			if (indicadorInscrDuplicadaAmbVirtual2.equals(String
					.valueOf(ConstantesSistema.SIM))) {
				indicadoresSituacao += indicadoresSituacao.length() > 0 ? ", " + SITUACAO_MATRICULA_DUPLICADA_AMB_VIRTUAL_2
						: "4";
			}
		}
		if (Util.verificarNaoVazio(indicadorInscrDuplicadaGSAN)) {
			if (indicadorInscrDuplicadaGSAN.equals(String
					.valueOf(ConstantesSistema.SIM))) {
				indicadoresSituacao += indicadoresSituacao.length() > 0 ? ", " + SITUACAO_MATRICULA_DUPLICADA_GSAN
						: "5";
			}
		}
		return indicadoresSituacao;
	}

	public String getIndicadorInscrDuplicadaGSAN() {
		return indicadorInscrDuplicadaGSAN;
	}

	public void setIndicadorInscrDuplicadaGSAN(
			String indicadorInscrDuplicadaGSAN) {
		this.indicadorInscrDuplicadaGSAN = indicadorInscrDuplicadaGSAN;
	}

}
