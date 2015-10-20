package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresaFoto;
import gcom.micromedicao.MovimentoRoteiroEmpresaFoto;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

public class Imagem{

	public static byte[] getImagem(Integer idFoto){
		byte[] foto = null; 
		
		FiltroMovimentoRoteiroEmpresaFoto filtroMovimentoRoteiroFoto = new FiltroMovimentoRoteiroEmpresaFoto();
		filtroMovimentoRoteiroFoto.adicionarParametro(
				new ParametroSimples(FiltroMovimentoRoteiroEmpresaFoto.ID, idFoto));

		Collection colMovimentoRoteiroFoto 
			= Fachada.getInstancia().pesquisar(filtroMovimentoRoteiroFoto, MovimentoRoteiroEmpresaFoto.class.getName());

		MovimentoRoteiroEmpresaFoto movimentoRoteiroFoto = (MovimentoRoteiroEmpresaFoto) Util.retonarObjetoDeColecao(colMovimentoRoteiroFoto);
				
		if(movimentoRoteiroFoto != null && movimentoRoteiroFoto.getFoto() !=null ){
			foto = movimentoRoteiroFoto.getFoto();
		}else{
			throw new ActionServletException("atencao.micromedicao.nao.possui.foto");
		}
		
		return foto;
	}
}
