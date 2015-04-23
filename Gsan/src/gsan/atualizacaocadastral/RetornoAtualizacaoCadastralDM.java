package gsan.atualizacaocadastral;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class RetornoAtualizacaoCadastralDM extends ObjetoTransacao {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Short codigoSituacao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private MensagemAtualizacaoCadastralDM mensagemAtualizacaoCadastralDM;

    /** persistent field */
    private ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastroDM;

    /** persistent field */
    private AtributoAtualizacaoCadastralDM atributoAtualizacaoCadastralDM;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Imovel imovel;
    
    private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM;
    
    private Short codigoAlteracao;
    
    private MedicaoTipo medicaoTipo;
    
    private Usuario usuario;
    
    private Date dataAtualizacao;
    
    public static final Short SITUACAO_ATUALIZADO = new Short("1");
    public static final Short SITUACAO_PENDENTE = new Short("2");
    public static final Short PENDENTE_POR_INSCRICAO = new Short("3");
    public static final Short PENDENTE_POR_LOGRADOURO = new Short("4");
    
    
    public static final Short APROVADO = new Short("1");
    public static final Short ACEITO = new Short("2");
    public static final Short REPROVADO = new Short("3");

    public RetornoAtualizacaoCadastralDM(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public MensagemAtualizacaoCadastralDM getMensagemAtualizacaoCadastralDM() {
		return mensagemAtualizacaoCadastralDM;
	}

	public void setMensagemAtualizacaoCadastralDM(
			MensagemAtualizacaoCadastralDM mensagemAtualizacaoCadastralDM) {
		this.mensagemAtualizacaoCadastralDM = mensagemAtualizacaoCadastralDM;
	}

	public ParametroTabelaAtualizacaoCadastralDM getParametroTabelaAtualizacaoCadastroDM() {
		return parametroTabelaAtualizacaoCadastroDM;
	}

	public void setParametroTabelaAtualizacaoCadastroDM(
			ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastroDM) {
		this.parametroTabelaAtualizacaoCadastroDM = parametroTabelaAtualizacaoCadastroDM;
	}

	public AtributoAtualizacaoCadastralDM getAtributoAtualizacaoCadastralDM() {
		return atributoAtualizacaoCadastralDM;
	}

	public void setAtributoAtualizacaoCadastralDM(
			AtributoAtualizacaoCadastralDM atributoAtualizacaoCadastralDM) {
		this.atributoAtualizacaoCadastralDM = atributoAtualizacaoCadastralDM;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	

	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastralDM() {
		return imovelAtualizacaoCadastralDM;
	}

	public void setImovelAtualizacaoCadastralDM(
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public Short getCodigoAlteracao() {
		return codigoAlteracao;
	}

	public void setCodigoAlteracao(Short codigoAlteracao) {
		this.codigoAlteracao = codigoAlteracao;
	}

	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

}
