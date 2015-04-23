package gsan.gui.micromedicao;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.micromedicao.FiltroMovimentoRoteiroEmpresaFoto;
import gsan.micromedicao.MovimentoRoteiroEmpresaFoto;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
