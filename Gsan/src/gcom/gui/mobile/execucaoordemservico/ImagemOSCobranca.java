package gcom.gui.mobile.execucaoordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.mobile.execucaoordemservico.ExecucaoOSFoto;

public class ImagemOSCobranca{

	public static byte[] getImagem(Integer idArquivo, Integer idOrdemServico, Integer idSituacao){

		ExecucaoOSFoto foto = Fachada.getInstancia().pesquisarFotosOSCobrancaSmartphone(idArquivo, idOrdemServico, idSituacao);
		if( foto != null ){
			return foto.getFoto();
		}else{
			throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
		}
	}
	
//	public static byte[] getImagemImovel(Integer idFoto){
//		byte[] foto = null; 
//		ArrayList<ImovelFotoAtualizacaoCadastral> fotos = (ArrayList<ImovelFotoAtualizacaoCadastral>) Fachada.getInstancia().pesquisarImovelFotoAtualizacaoCadastral(idFoto, false);
//		if(!Util.isVazioOrNulo(fotos)){
//			foto = fotos.get(0).getFotoImovel();
//		}
//		return foto;
//	}
}