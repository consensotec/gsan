package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.OrdemServicoFoto;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.util.Util;

import java.util.ArrayList;

public class Imagem{

	public static byte[] getImagem(Integer idFoto){
		byte[] foto = null; 
		ArrayList<OrdemServicoFoto> fotos = (ArrayList<OrdemServicoFoto>) Fachada.getInstancia().pesquisarFotosOrdemServico(idFoto, false);
		if(!Util.isVazioOrNulo(fotos)){
			foto = fotos.get(0).getFoto();
		}else{
			throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
		}
		return foto;
	}
}