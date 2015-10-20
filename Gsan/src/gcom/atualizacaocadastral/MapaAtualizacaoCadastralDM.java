package gcom.atualizacaocadastral;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class MapaAtualizacaoCadastralDM extends ObjetoGcom {
	private static final long serialVersionUID = -7773550372045562798L;

	private Integer id;
    private Localidade localidade;
    private SetorComercial setorComercial;
    private Quadra quadra;
    private Usuario usuario;
    private byte[] arquivoKml;
    private byte[] arquivoMap;
    private byte[] arquivoJson;
	private Double coordenadaX;
	private Double coordenadaY;
	private Date ultimaAlteracao;

	public MapaAtualizacaoCadastralDM() {
		super();
	}

	public MapaAtualizacaoCadastralDM(Integer id, Localidade localidade, SetorComercial setorComercial, Quadra quadra,
			Usuario usuario, byte[] arquivoKml, byte[] arquivoMap, byte[] arquivoJson, Double coordenadaX,
			Double coordenadaY, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.quadra = quadra;
		this.usuario = usuario;
		this.arquivoKml = arquivoKml;
		this.arquivoMap = arquivoMap;
		this.arquivoJson = arquivoJson;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public byte[] getArquivoKml() {
		return arquivoKml;
	}

	public void setArquivoKml(byte[] arquivoKml) {
		this.arquivoKml = arquivoKml;
	}

	public byte[] getArquivoMap() {
		return arquivoMap;
	}

	public void setArquivoMap(byte[] arquivoMap) {
		this.arquivoMap = arquivoMap;
	}

	public byte[] getArquivoJson() {
		return arquivoJson;
	}

	public void setArquivoJson(byte[] arquivoJson) {
		this.arquivoJson = arquivoJson;
	}

	public Double getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Double getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
    public String[] retornaCamposChavePrimaria(){
		return new String[] { "id" };
	}
}
