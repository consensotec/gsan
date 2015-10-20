package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoFoto;
import gcom.atendimentopublico.ordemservico.FiscalizacaoFoto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
