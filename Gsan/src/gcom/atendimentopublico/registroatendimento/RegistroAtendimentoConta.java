package gcom.atendimentopublico.registroatendimento;

import gcom.faturamento.conta.ContaGeral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class RegistroAtendimentoConta extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private RegistroAtendimentoContaPK comp_id;
	
	private Date ultimaAlteracao;
	
	private RegistroAtendimento registroAtendimento;
	
	private ContaGeral contaGeral;
	
	
	public RegistroAtendimentoContaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(RegistroAtendimentoContaPK comp_id) {
		this.comp_id = comp_id;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}
}
