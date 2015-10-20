package gcom.atualizacaocadastral.bean;

import gcom.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;

public class ImovelGeracaoTabelasTemporariasCadastroDMHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private Collection colecaoMatriculas;
	private String matricula;
	private String nomeMatricula;
	private String cliente;
	private String nomeCliente;
	private String sugestao;
	private String firma;
	private String nomeFirma;
	private Integer quantidadeMaxima;
	private String elo;
	private String nomeElo;
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	private String setorComercialInicial;
	private String nomeSetorComercialInicial;
	private String setorComercialFinal;
	private String nomeSetorComercialFinal;
	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String idQuadraInicial;
	private String idQuadraFinal;
	private String quadraInicial;
	private String quadraFinal;
	private Short rotaInicial;
	private Integer rotaSequenciaInicial;
	private Short rotaFinal;
	private Integer rotaSequenciaFinal;
	private String perfilImovel;
	private String nomePerfilImovel;
	private String categoria;
	private String nomeCategoria;
	private String subCategoria;
	private String nomeSubCategoria;
	private String idSituacaoLigacaoAgua;
	private String nomeSituacaoLigacaoAgua;
	private String imovelSituacao;
	private String msgQuadraInicial;
	private String msgQuadraFinal;
	private String quadraMensagemOrigem;
	private ParametroTabelaAtualizacaoCadastralDM parametro;
	
	private Usuario usuario;
	
	
	private Collection colecaoImovel;
	
	private String empresa;	
	private String localidade;
	private String codigoSetorComercial;
	private Collection<Integer> numeroQuadra;
	private Collection<Integer> codigoRota;
	public Collection getColecaoMatriculas() {
		return colecaoMatriculas;
	}
	public void setColecaoMatriculas(Collection colecaoMatriculas) {
		this.colecaoMatriculas = colecaoMatriculas;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeMatricula() {
		return nomeMatricula;
	}
	public void setNomeMatricula(String nomeMatricula) {
		this.nomeMatricula = nomeMatricula;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public Integer getQuantidadeMaxima() {
		return quantidadeMaxima;
	}
	public void setQuantidadeMaxima(Integer quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}
	public String getElo() {
		return elo;
	}
	public void setElo(String elo) {
		this.elo = elo;
	}
	public String getNomeElo() {
		return nomeElo;
	}
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}
	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}
	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}
	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}
	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}
	public String getQuadraInicial() {
		return quadraInicial;
	}
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	public String getQuadraFinal() {
		return quadraFinal;
	}
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	public Short getRotaInicial() {
		return rotaInicial;
	}
	public void setRotaInicial(Short rotaInicial) {
		this.rotaInicial = rotaInicial;
	}
	public Integer getRotaSequenciaInicial() {
		return rotaSequenciaInicial;
	}
	public void setRotaSequenciaInicial(Integer rotaSequenciaInicial) {
		this.rotaSequenciaInicial = rotaSequenciaInicial;
	}
	public Short getRotaFinal() {
		return rotaFinal;
	}
	public void setRotaFinal(Short rotaFinal) {
		this.rotaFinal = rotaFinal;
	}
	public Integer getRotaSequenciaFinal() {
		return rotaSequenciaFinal;
	}
	public void setRotaSequenciaFinal(Integer rotaSequenciaFinal) {
		this.rotaSequenciaFinal = rotaSequenciaFinal;
	}
	public String getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getNomePerfilImovel() {
		return nomePerfilImovel;
	}
	public void setNomePerfilImovel(String nomePerfilImovel) {
		this.nomePerfilImovel = nomePerfilImovel;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public String getNomeSubCategoria() {
		return nomeSubCategoria;
	}
	public void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}
	public String getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	public void setIdSituacaoLigacaoAgua(String idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	public String getNomeSituacaoLigacaoAgua() {
		return nomeSituacaoLigacaoAgua;
	}
	public void setNomeSituacaoLigacaoAgua(String nomeSituacaoLigacaoAgua) {
		this.nomeSituacaoLigacaoAgua = nomeSituacaoLigacaoAgua;
	}
	public String getImovelSituacao() {
		return imovelSituacao;
	}
	public void setImovelSituacao(String imovelSituacao) {
		this.imovelSituacao = imovelSituacao;
	}
	public String getMsgQuadraInicial() {
		return msgQuadraInicial;
	}
	public void setMsgQuadraInicial(String msgQuadraInicial) {
		this.msgQuadraInicial = msgQuadraInicial;
	}
	public String getMsgQuadraFinal() {
		return msgQuadraFinal;
	}
	public void setMsgQuadraFinal(String msgQuadraFinal) {
		this.msgQuadraFinal = msgQuadraFinal;
	}
	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}
	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}
	public ParametroTabelaAtualizacaoCadastralDM getParametro() {
		return parametro;
	}
	public void setParametro(ParametroTabelaAtualizacaoCadastralDM parametro) {
		this.parametro = parametro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Collection getColecaoImovel() {
		return colecaoImovel;
	}
	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Collection<Integer> getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(Collection<Integer> numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Collection<Integer> getCodigoRota() {
		return codigoRota;
	}
	public void setCodigoRota(Collection<Integer> codigoRota) {
		this.codigoRota = codigoRota;
	} 

}
