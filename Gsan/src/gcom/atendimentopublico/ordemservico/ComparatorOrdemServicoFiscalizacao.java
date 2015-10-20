package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.util.Util;

import java.util.Comparator;

public class ComparatorOrdemServicoFiscalizacao implements Comparator<OrdemServico>{

	public int compare(OrdemServico o1, OrdemServico o2) {

		Localidade localidade1 = null;
		Localidade localidade2 = null;
		
		if (o1.getImovel() != null && o1.getImovel().getLocalidade() != null) {
			localidade1 = o1.getImovel().getLocalidade();
		} else {
			localidade1 = o1.getRegistroAtendimento().getLocalidade();
		}
		
		if (o2.getImovel() != null && o2.getImovel().getLocalidade() != null) {
			localidade2 = o2.getImovel().getLocalidade();
		} else {
			localidade2 = o2.getRegistroAtendimento().getLocalidade();
		}
		
		if(Util.formataAnoMes(o1.getDataGeracao())
				.compareTo(Util.formataAnoMes(o2.getDataGeracao())) <= 0
			&& localidade1.getGerenciaRegional().getId()
					.compareTo(localidade2.getGerenciaRegional().getId()) <= 0
			&& localidade1.getUnidadeNegocio().getId()
					.compareTo(localidade2.getUnidadeNegocio().getId()) <= 0
			&& localidade1.getId()
					.compareTo(localidade2.getId()) <= 0
			&& o1.getId()
					.compareTo(o2.getId()) <= 0
			&& ((o1.getOsReferencia().getOsReferidaRetornoTipo() == null
				|| o2.getOsReferencia().getOsReferidaRetornoTipo() == null)
				|| (o1.getOsReferencia().getOsReferidaRetornoTipo() != null
					&& o2.getOsReferencia().getOsReferidaRetornoTipo() != null
					&& o1.getOsReferencia().getOsReferidaRetornoTipo().getId()
							.compareTo(o2.getOsReferencia().getOsReferidaRetornoTipo().getId()) <= 0)
			&& ((o1.getOsReferencia().getAtendimentoMotivoEncerramento() == null
					|| o2.getOsReferencia().getAtendimentoMotivoEncerramento() == null)
					|| (o1.getOsReferencia().getAtendimentoMotivoEncerramento() != null
							&& o2.getOsReferencia().getAtendimentoMotivoEncerramento() != null
							&& o1.getOsReferencia().getAtendimentoMotivoEncerramento().getId()
							.compareTo(o2.getOsReferencia().getAtendimentoMotivoEncerramento().getId()) <= 0)))) {
			return -1;
			
		}else{
			return +1;
		}
		
	}

}
