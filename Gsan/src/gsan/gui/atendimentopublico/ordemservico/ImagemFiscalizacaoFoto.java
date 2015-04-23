package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.FiltroFiscalizacaoFoto;
import gsan.atendimentopublico.ordemservico.FiscalizacaoFoto;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

public class ImagemFiscalizacaoFoto{

	public static byte[] getImagem(Integer idFoto){
		byte[] foto = null; 
		
		FiltroFiscalizacaoFoto filtro = new FiltroFiscalizacaoFoto();
		filtro.adicionarParametro(new ParametroSimples(FiltroFiscalizacaoFoto.ID, idFoto));

		Collection colFiscalizacaoFoto
			= Fachada.getInstancia().pesquisar(filtro, FiscalizacaoFoto.class.getName());

		FiscalizacaoFoto fiscalizacaoFoto = (FiscalizacaoFoto) Util.retonarObjetoDeColecao(colFiscalizacaoFoto);
				
		if(fiscalizacaoFoto != null && fiscalizacaoFoto.getArquivo() !=null ){
			foto = fiscalizacaoFoto.getArquivo();
		}else{
			throw new ActionServletException("atencao.micromedicao.nao.possui.foto");
		}
		
		return foto;
	}
}
