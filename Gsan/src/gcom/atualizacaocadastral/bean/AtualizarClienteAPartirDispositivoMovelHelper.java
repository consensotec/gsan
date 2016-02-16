package gcom.atualizacaocadastral.bean;

import java.io.Serializable;
import java.util.Collection;

import gcom.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gcom.seguranca.acesso.usuario.Usuario;

/**
 * 
 * [UC1222] Atualizar Cliente a Partir do Dispositivo Movel
 * 
 * @author Arthur Carvalho
 *
 * @date 12/09/2013
 * 
 */
public class AtualizarClienteAPartirDispositivoMovelHelper implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
    private String matricula;
    private Integer tipoOperacao;
    private String idClienteAnterior;
    private String nomeAnterior;
    private String cpfAnterior;
    private String cnpjAnterior;
    private String rgAnterior;
    private String unidadeFederacaoAnterior;
    private String orgaoExpedidorAnterior;
    
    private String nomeNovo;
    private String cpfNovo;
    private String cnpjNovo;
    private String rgNovo;
    private String unidadeFederacaoNovo;
    private String orgaoExpedidorNovo;

    private boolean removerTelefone;
    private boolean atualizarMovimentoClienteFone;
    private String idArquivoTextoRetornoClienteFoneVisitaCampo;
    private Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> clienteFoneHelper;
    private Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> clienteFoneAnteriorHelper;
    private Usuario usuario;
    
    // atualizacao cadastral
    private String dataNascimento;
    private Integer idPessoaSexo;
    private String dataEmissaoRG;
    private String nomeMae;
    private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral;
    private Integer idClienteAtualizacaoCadastral;
    private boolean clienteAtualizar;
    private boolean clienteValidar;
    
    private Integer idClienteTipo;
    
    private Short indicadorResponsavel;
    private Short indicadorDocumentacao;
    private Short indicadorProprietario;
    
    //registrar transacao
    private boolean indicadorRegistrarTransacao;
    
	
    public String getNomeAnterior() {
		return nomeAnterior;
	}
	public void setNomeAnterior(String nomeAnterior) {
		this.nomeAnterior = nomeAnterior;
	}
	public String getCpfAnterior() {
		return cpfAnterior;
	}
	public void setCpfAnterior(String cpfAnterior) {
		this.cpfAnterior = cpfAnterior;
	}
	public String getCnpjAnterior() {
		return cnpjAnterior;
	}
	public void setCnpjAnterior(String cnpjAnterior) {
		this.cnpjAnterior = cnpjAnterior;
	}
	public String getRgAnterior() {
		return rgAnterior;
	}
	public void setRgAnterior(String rgAnterior) {
		this.rgAnterior = rgAnterior;
	}
	public String getUnidadeFederacaoAnterior() {
		return unidadeFederacaoAnterior;
	}
	public void setUnidadeFederacaoAnterior(String unidadeFederacaoAnterior) {
		this.unidadeFederacaoAnterior = unidadeFederacaoAnterior;
	}
	public String getOrgaoExpedidorAnterior() {
		return orgaoExpedidorAnterior;
	}
	public void setOrgaoExpedidorAnterior(String orgaoExpedidorAnterior) {
		this.orgaoExpedidorAnterior = orgaoExpedidorAnterior;
	}
	public String getNomeNovo() {
		return nomeNovo;
	}
	public void setNomeNovo(String nomeNovo) {
		this.nomeNovo = nomeNovo;
	}
	public String getCpfNovo() {
		return cpfNovo;
	}
	public void setCpfNovo(String cpfNovo) {
		this.cpfNovo = cpfNovo;
	}
	public String getCnpjNovo() {
		return cnpjNovo;
	}
	public void setCnpjNovo(String cnpjNovo) {
		this.cnpjNovo = cnpjNovo;
	}
	public String getRgNovo() {
		return rgNovo;
	}
	public void setRgNovo(String rgNovo) {
		this.rgNovo = rgNovo;
	}
	public String getUnidadeFederacaoNovo() {
		return unidadeFederacaoNovo;
	}
	public void setUnidadeFederacaoNovo(String unidadeFederacaoNovo) {
		this.unidadeFederacaoNovo = unidadeFederacaoNovo;
	}
	public String getOrgaoExpedidorNovo() {
		return orgaoExpedidorNovo;
	}
	public void setOrgaoExpedidorNovo(String orgaoExpedidorNovo) {
		this.orgaoExpedidorNovo = orgaoExpedidorNovo;
	}
	public String getIdClienteAnterior() {
		return idClienteAnterior;
	}
	public void setIdClienteAnterior(String idClienteAnterior) {
		this.idClienteAnterior = idClienteAnterior;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> getClienteFoneHelper() {
		return clienteFoneHelper;
	}
	public void setClienteFoneHelper(
			Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> clienteFoneHelper) {
		this.clienteFoneHelper = clienteFoneHelper;
	}
	public Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> getClienteFoneAnteriorHelper() {
		return clienteFoneAnteriorHelper;
	}
	public void setClienteFoneAnteriorHelper(
			Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> clienteFoneAnteriorHelper) {
		this.clienteFoneAnteriorHelper = clienteFoneAnteriorHelper;
	}
	public boolean isRemoverTelefone() {
		return removerTelefone;
	}
	public void setRemoverTelefone(boolean removerTelefone) {
		this.removerTelefone = removerTelefone;
	}
	public boolean isAtualizarMovimentoClienteFone() {
		return atualizarMovimentoClienteFone;
	}
	public void setAtualizarMovimentoClienteFone(
			boolean atualizarMovimentoClienteFone) {
		this.atualizarMovimentoClienteFone = atualizarMovimentoClienteFone;
	}
	public String getIdArquivoTextoRetornoClienteFoneVisitaCampo() {
		return idArquivoTextoRetornoClienteFoneVisitaCampo;
	}
	public void setIdArquivoTextoRetornoClienteFoneVisitaCampo(
			String idArquivoTextoRetornoClienteFoneVisitaCampo) {
		this.idArquivoTextoRetornoClienteFoneVisitaCampo = idArquivoTextoRetornoClienteFoneVisitaCampo;
	}
	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}
	public void setImovelAtualizacaoCadastral(
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral) {
		this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
	}
	public Integer getIdClienteAtualizacaoCadastral() {
		return idClienteAtualizacaoCadastral;
	}
	public void setIdClienteAtualizacaoCadastral(
			Integer idClienteAtualizacaoCadastral) {
		this.idClienteAtualizacaoCadastral = idClienteAtualizacaoCadastral;
	}
	public boolean isClienteAtualizar() {
		return clienteAtualizar;
	}
	public void setClienteAtualizar(boolean clienteAtualizar) {
		this.clienteAtualizar = clienteAtualizar;
	}
	public boolean isClienteValidar() {
		return clienteValidar;
	}
	public void setClienteValidar(boolean clienteValidar) {
		this.clienteValidar = clienteValidar;
	}
	public boolean isIndicadorRegistrarTransacao() {
		return indicadorRegistrarTransacao;
	}
	public void setIndicadorRegistrarTransacao(boolean indicadorRegistrarTransacao) {
		this.indicadorRegistrarTransacao = indicadorRegistrarTransacao;
	}
	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}
	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getIdPessoaSexo() {
		return idPessoaSexo;
	}
	public void setIdPessoaSexo(Integer idPessoaSexo) {
		this.idPessoaSexo = idPessoaSexo;
	}
	public String getDataEmissaoRG() {
		return dataEmissaoRG;
	}
	public void setDataEmissaoRG(String dataEmissaoRG) {
		this.dataEmissaoRG = dataEmissaoRG;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public Integer getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public Short getIndicadorResponsavel() {
		return indicadorResponsavel;
	}
	public void setIndicadorResponsavel(Short indicadorResponsavel) {
		this.indicadorResponsavel = indicadorResponsavel;
	}
	public Short getIndicadorDocumentacao() {
		return indicadorDocumentacao;
	}
	public void setIndicadorDocumentacao(Short indicadorDocumentacao) {
		this.indicadorDocumentacao = indicadorDocumentacao;
	}
	public Short getIndicadorProprietario() {
		return indicadorProprietario;
	}
	public void setIndicadorProprietario(Short indicadorProprietario) {
		this.indicadorProprietario = indicadorProprietario;
	}
	
}