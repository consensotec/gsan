package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.util.Util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * Filtro responsável por auxiliar na geração do relatório de 
 * OS por Situação RelatorioOSSituacao
 * 
 * @author Diogo Peixoto
 * @since 09/06/2011
 * 
 */
public class FiltrarRelatorioOSSituacaoHelper implements Serializable{

	private static final long serialVersionUID = 1L;

	//Campos obrigatórios
	private String opcaoRelatorio;
	private Integer periodoReferenciaInicial;
	private Integer periodoReferenciaFinal;
	private Integer referenciaCobranca;
	private String dataReferenciaInicial;
	private String dataReferenciaFinal;
	private Integer idEmpresa;
	private String indicadorOSSemGrupo;
	private String situacaoOS;
	private Integer idGrupoCobranca;
	private Integer idContratoCobranca;
	private Short cobranca;
	private Short seletiva;
	private Short atendimento;
	private ArrayList<Integer> motivoEncerramento;
	private ArrayList<Integer> retornoFiscalizacao;
	
	//Campos não obrigatórios
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Localidade localidade;
	private SetorComercial setorComercial;
	private Quadra quadra;
	private String opcaoOSCobranca;
	private Localidade eloPolo;
	private ServicoTipo servicoTipo;
	private String imoveSuperior;
	
	public FiltrarRelatorioOSSituacaoHelper(){
		
	}

	public FiltrarRelatorioOSSituacaoHelper(String opcaoRel, Integer periodoReferenciaInicial,Integer periodoReferenciaFinal, Integer idEmpresa, String situacaoOS, Integer idGrupoCobranca,
			GerenciaRegional gerencia, UnidadeNegocio unidade, Localidade localidade, SetorComercial setor, Quadra quadra, String opcaoCobranca, 
			Localidade eloPolo, ServicoTipo servicoTipo){
		
		this.opcaoRelatorio = opcaoRel;
		this.periodoReferenciaInicial = periodoReferenciaInicial;
		this.periodoReferenciaFinal = periodoReferenciaFinal;
		this.idEmpresa = idEmpresa;
		this.situacaoOS = situacaoOS;
		this.idGrupoCobranca = idGrupoCobranca;
		this.gerenciaRegional = gerencia;
		this.unidadeNegocio = unidade;
		this.localidade = localidade;
		this.setorComercial = setor;
		this.quadra = quadra;
		this.opcaoOSCobranca = opcaoCobranca;
		this.eloPolo = eloPolo;
		this.servicoTipo = servicoTipo;
	}
	
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}


	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getSituacaoOS() {
		return situacaoOS;
	}

	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public Integer getPeriodoReferenciaInicial() {
		return periodoReferenciaInicial;
	}

	public void setPeriodoReferenciaInicial(Integer periodoReferenciaInicial) {
		this.periodoReferenciaInicial = periodoReferenciaInicial;
	}

	public Integer getPeriodoReferenciaFinal() {
		return periodoReferenciaFinal;
	}

	public void setPeriodoReferenciaFinal(Integer periodoReferenciaFinal) {
		this.periodoReferenciaFinal = periodoReferenciaFinal;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String getOpcaoOSCobranca() {
		return opcaoOSCobranca;
	}

	public void setOpcaoOSCobranca(String opcaoOSCobranca) {
		this.opcaoOSCobranca = opcaoOSCobranca;
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

	public String getDataReferenciaInicial() {
		return dataReferenciaInicial;
	}

	public void setDataReferenciaInicial(String dataReferenciaInicial) {
		this.dataReferenciaInicial = dataReferenciaInicial;
	}

	public String getDataReferenciaFinal() {
		return dataReferenciaFinal;
	}

	public void setDataReferenciaFinal(String dataReferenciaFinal) {
		this.dataReferenciaFinal = dataReferenciaFinal;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public Short getCobranca() {
		return cobranca;
	}

	public void setCobranca(Short cobranca) {
		this.cobranca = cobranca;
	}

	public Short getSeletiva() {
		return seletiva;
	}

	public void setSeletiva(Short seletiva) {
		this.seletiva = seletiva;
	}

	public Short getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Short atendimento) {
		this.atendimento = atendimento;
	}

	public String getImoveSuperior() {
		return imoveSuperior;
	}

	public void setImoveSuperior(String imoveSuperior) {
		this.imoveSuperior = imoveSuperior;
	}

	public ArrayList<Integer> getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(ArrayList<Integer> motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public ArrayList<Integer> getRetornoFiscalizacao() {
		return retornoFiscalizacao;
	}

	public void setRetornoFiscalizacao(ArrayList<Integer> retornoFiscalizacao) {
		this.retornoFiscalizacao = retornoFiscalizacao;
	}

	public Integer getIdContratoCobranca() {
		return idContratoCobranca;
	}

	public void setIdContratoCobranca(Integer idContratoCobranca) {
		this.idContratoCobranca = idContratoCobranca;
	}

	public String getIndicadorOSSemGrupo() {
		return indicadorOSSemGrupo;
	}

	public void setIndicadorOSSemGrupo(String indicadorOSSemGrupo) {
		this.indicadorOSSemGrupo = indicadorOSSemGrupo;
	}

	public Integer getReferenciaCobranca() {
		return referenciaCobranca;
	}

	public void setReferenciaCobranca(Integer referenciaCobranca) {
		this.referenciaCobranca = referenciaCobranca;
	}
	
}