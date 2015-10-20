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
package gcom.cadastro.cliente;

import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

/**
 * @author Arthur Carvalho
 * @created 15 de dezembro de 2011
 */
public class ClienteVirtual extends ObjetoTransacao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	
	private String cpf;
	
	private String rg;
	
	private Date dataEmissaoRg;
	
	private Date dataNascimento;
	
	private String cnpj;
	
	private String email;
	
	private String nomeMae;
	
	private Short indicadorPessoaFisicaJuridica;
	
	private String ddd;
	
	private String numeroTelefone;
	
	private String numeroFoneRamal;
	
	private String nomeContato;
	
	private Short indicadorAtualizado;
	
	private Date ultimaAlteracao;
	
	private Imovel imovel;
	
	private OrgaoExpedidorRg orgaoExpedidorRg;
	
	private UnidadeFederacao unidadeFederacao;
	
	private PessoaSexo pessoaSexo;
	
	private Profissao profissao;
	
	private RamoAtividade ramoAtividade;
	
	private FoneTipo foneTipo;
	

	 public ClienteVirtual(){
		 
	 }


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


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}


	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomeMae() {
		return nomeMae;
	}


	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}


	public Short getIndicadorPessoaFisicaJuridica() {
		return indicadorPessoaFisicaJuridica;
	}


	public void setIndicadorPessoaFisicaJuridica(Short indicadorPessoaFisicaJuridica) {
		this.indicadorPessoaFisicaJuridica = indicadorPessoaFisicaJuridica;
	}


	public String getDdd() {
		return ddd;
	}


	public void setDdd(String ddd) {
		this.ddd = ddd;
	}


	public String getNumeroTelefone() {
		return numeroTelefone;
	}


	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}


	public String getNumeroFoneRamal() {
		return numeroFoneRamal;
	}


	public void setNumeroFoneRamal(String numeroFoneRamal) {
		this.numeroFoneRamal = numeroFoneRamal;
	}


	public String getNomeContato() {
		return nomeContato;
	}


	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}


	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}


	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDataFormatada() {
		return Util.formatarData(getUltimaAlteracao());
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}


	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}


	public Imovel getImovel() {
		return imovel;
	}


	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	public OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return orgaoExpedidorRg;
	}


	public void setOrgaoExpedidorRg(OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}


	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}


	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}


	public PessoaSexo getPessoaSexo() {
		return pessoaSexo;
	}


	public void setPessoaSexo(PessoaSexo pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}


	public Profissao getProfissao() {
		return profissao;
	}


	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}


	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}


	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}


	public FoneTipo getFoneTipo() {
		return foneTipo;
	}


	public void setFoneTipo(FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}
	 
	
	public String getHint(){
		Imovel imovel = this.getImovel();
		FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
		filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.ID_IMOVEL, imovel.getId()));
		filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.INDICADOR_ATUALIZADO, ConstantesSistema.NAO));
		filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.FONE_TIPO);
		filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.RAMO_ATIVIDADE);
		filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.PESSOA_SEXO);
		filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.PROFISSAO);
		filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.ORGAO_EXPEDIDOR);
		filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.UNIDADE_FEDERACAO);
		
		ClienteVirtual clienteVirtual = (ClienteVirtual) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroClienteVirtual, ClienteVirtual.class.getName()));
		
		
		String hint = "";
		hint = "Imovel:" + imovel.getId() + "<br>";
		hint = hint + "E-mail:"+ clienteVirtual.getEmail() + "<br>";
		hint = hint + "Tipo de Telefone:" + clienteVirtual.getFoneTipo().getDescricao()+ "<br>";
		hint = hint + "DDD:" + clienteVirtual.getDdd() + "<br>";
		hint = hint + "Numero do Telefone:" + clienteVirtual.getNumeroTelefone()+ "<br>";
		if ( clienteVirtual.getNumeroFoneRamal() != null && !clienteVirtual.getNumeroFoneRamal().equals("") ) {
			hint = hint + "Ramal:" + clienteVirtual.getNumeroFoneRamal() + "<br>";
		}
		hint = hint + "Nome do Contato:" + clienteVirtual.getNomeContato() + "<br>";

		if ( clienteVirtual.getIndicadorPessoaFisicaJuridica().equals(Short.valueOf("1")) ) {
			hint = hint + "Data de emissão:" + Util.formatarData(clienteVirtual.getDataEmissaoRg())+ "<br>";
			hint = hint + "Orgao Expedidor:" + clienteVirtual.getOrgaoExpedidorRg().getDescricao() + "<br>";
			hint = hint + "Unidade Federação:" + clienteVirtual.getUnidadeFederacao().getSigla() + "<br>";
			hint = hint + "Data Nascimento:" + Util.formatarData(clienteVirtual.getDataNascimento()) + "<br>";
			hint = hint + "Profissão:" + clienteVirtual.getProfissao().getDescricao() + "<br>";
			hint = hint + "Nome da mae:" + clienteVirtual.getNomeMae() + "<br>";
			hint = hint + "Pessoa Sexo:" + clienteVirtual.getPessoaSexo().getDescricao() + "<br>";
		} else {
			hint = hint + "Ramo de Atividade:" + clienteVirtual.getRamoAtividade().getDescricao() + "<br>";
		}
		
		return hint;
	}
	
	public boolean getVerificaCpfCnpjCadastrado(){
		boolean existe = false;
		
		String cpfCnpj = null;
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		if ( getCnpj() != null ) {
			cpfCnpj = getCnpj();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, cpfCnpj));
		} else {
			cpfCnpj = getCpf();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, cpfCnpj));
		}
		
		Cliente cliente = new Cliente();
		Collection<Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
		if ( colecaoCliente != null && !colecaoCliente.isEmpty() ) {
			existe = true;
		}
		
		return existe;
	}
	 
	public String getCpfFormatado(){
		
		return Util.formatarCpf(getCpf());
	}
	
	public String getCnpjFormatado(){
		return Util.formatarCnpj(getCnpj());
	}
}
