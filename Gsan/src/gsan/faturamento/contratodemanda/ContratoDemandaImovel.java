package gsan.faturamento.contratodemanda;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.seguranca.acesso.usuario.Usuario;

import java.util.Date;

/**
 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 20/09/2011
 *
 */
public class ContratoDemandaImovel{

	private Integer id;
	private String numeroContrato;
	private Imovel imovel;
	private Cliente cliente;
	private Short relacaoCliente;
	private Date dataInicioContrato;
	private Date dataFimContrato;
	private Integer demandaMinimaContratada;
	private ContratoDemandaFaixaConsumo faixaConsumo;
	private Usuario usuarioIncluiuContrato;
	private Date dataInclusaoContrato;
	private Date dataEncerramentoContrato;
	private Date dataSuspensaoContrato;
	private ContratoDemandaMotivoEncerramento motivoEcenrramento;
	private ContratoDemandaSituacao situacao;
	private String descricaoObservacaoSuspensao;
	private String descricaoObservacaoEncerramento;
	private Usuario usuarioEncerrouContrato;
	private Date ultimaAlteracao;
	
	public ContratoDemandaImovel(){
		
	}

	/**
	 * Construtor mínimo. Todos os parâmetros obrigatórios para inserir um contrato de demanda
	 * do imóvel está presente neste construtor.
	 * 
	 * @author Diogo Peixoto
	 * @since 21/09/2011
	 * 
	 * @param numeroContrato
	 * @param imovel
	 * @param cliente
	 * @param relacaoCliente
	 * @param dataInicio
	 * @param dataFim
	 * @param demandaContratada
	 * @param faixaConsumo
	 * @param usuario
	 * @param dataInclusao
	 * @param ultimaAlteracao
	 * @param situacao
	 */
	public ContratoDemandaImovel(String numeroContrato, Imovel imovel, Cliente cliente, Short relacaoCliente, Date dataInicio, Date dataFim,
			Integer demandaContratada, ContratoDemandaFaixaConsumo faixaConsumo, Usuario usuario, Date dataInclusao, Date ultimaAlteracao, ContratoDemandaSituacao situacao){
		
		this.numeroContrato = numeroContrato;
		this.imovel = imovel;
		this.cliente = cliente;
		this.relacaoCliente = relacaoCliente;
		this.dataInicioContrato = dataInicio;
		this.dataFimContrato = dataFim;
		this.demandaMinimaContratada = demandaContratada;
		this.faixaConsumo = faixaConsumo;
		this.usuarioIncluiuContrato = usuario;
		this.dataInclusaoContrato = dataInclusao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.situacao = situacao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Short getRelacaoCliente() {
		return relacaoCliente;
	}
	public void setRelacaoCliente(Short relacaoCliente) {
		this.relacaoCliente = relacaoCliente;
	}
	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}
	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}
	public Date getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}
	public Integer getDemandaMinimaContratada() {
		return demandaMinimaContratada;
	}
	public void setDemandaMinimaContratada(Integer demandaMinimaContratada) {
		this.demandaMinimaContratada = demandaMinimaContratada;
	}
	public ContratoDemandaFaixaConsumo getFaixaConsumo() {
		return faixaConsumo;
	}
	public void setFaixaConsumo(ContratoDemandaFaixaConsumo faixaConsumo) {
		this.faixaConsumo = faixaConsumo;
	}
	public Usuario getUsuarioIncluiuContrato() {
		return usuarioIncluiuContrato;
	}
	public void setUsuarioIncluiuContrato(Usuario usuarioIncluiuContrato) {
		this.usuarioIncluiuContrato = usuarioIncluiuContrato;
	}
	public Date getDataInclusaoContrato() {
		return dataInclusaoContrato;
	}
	public void setDataInclusaoContrato(Date dataInclusaoContrato) {
		this.dataInclusaoContrato = dataInclusaoContrato;
	}
	public Date getDataEncerramentoContrato() {
		return dataEncerramentoContrato;
	}
	public void setDataEncerramentoContrato(Date dataEncerramentoContrato) {
		this.dataEncerramentoContrato = dataEncerramentoContrato;
	}
	public Date getDataSuspensaoContrato() {
		return dataSuspensaoContrato;
	}
	public void setDataSuspensaoContrato(Date dataSuspensaoContrato) {
		this.dataSuspensaoContrato = dataSuspensaoContrato;
	}
	public ContratoDemandaMotivoEncerramento getMotivoEcenrramento() {
		return motivoEcenrramento;
	}
	public void setMotivoEcenrramento(ContratoDemandaMotivoEncerramento motivoEcenrramento) {
		this.motivoEcenrramento = motivoEcenrramento;
	}
	public ContratoDemandaSituacao getSituacao() {
		return situacao;
	}
	public void setSituacao(ContratoDemandaSituacao situacao) {
		this.situacao = situacao;
	}
	public String getDescricaoObservacaoSuspensao() {
		return descricaoObservacaoSuspensao;
	}
	public void setDescricaoObservacaoSuspensao(String descricaoObservacaoSuspensao) {
		this.descricaoObservacaoSuspensao = descricaoObservacaoSuspensao;
	}
	public String getDescricaoObservacaoEncerramento() {
		return descricaoObservacaoEncerramento;
	}
	public void setDescricaoObservacaoEncerramento(String descricaoObservacaoEncerramento) {
		this.descricaoObservacaoEncerramento = descricaoObservacaoEncerramento;
	}
	public Usuario getUsuarioEncerrouContrato() {
		return usuarioEncerrouContrato;
	}
	public void setUsuarioEncerrouContrato(Usuario usuarioEncerrouContrato) {
		this.usuarioEncerrouContrato = usuarioEncerrouContrato;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}