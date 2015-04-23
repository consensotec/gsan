package gsan.gui.cadastro.dispositivomovel;

import gsan.cadastro.FiltroSistemaAndroid;
import gsan.cadastro.FiltroVersaoSistemasAndroid;
import gsan.cadastro.SistemaAndroid;
import gsan.cadastro.VersaoSistemasAndroid;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * [UC1308] Upload nova versão Sistemas Android
 * 
 *   Este caso de uso permite realizar o envio dos arquivos referentes à 
 *   nova versão do GSAN Dispositivo Móvel.
 * 
 * @author Fernanda Almeida
 * @since 03/04/2012
 *
 */
public class UploadVersaoSistemasAndroidAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		try{
			
			String versao =null;
			
			Integer idSistemaAndroid = null;
			
			FileItem arquivoApk = null;	
				
			Fachada fachada = Fachada.getInstancia();			

			DiskFileUpload upload = new DiskFileUpload();
			
			List<?> items;
		
			items = upload.parseRequest(httpServletRequest);
			FileItem item = null;
			
			Iterator<?> iter = items.iterator();
			
			
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				if (item.getFieldName().equals("versao")) {
					versao = new String(item.getString());
				}
				
				if (item.getFieldName().equals("idSistemaAndroid")) {
					idSistemaAndroid = new Integer(item.getString());
				}
				
				// verifica se não é diretorio
				if (!item.isFormField()) {
					
					String nomeItem = item.getName().toUpperCase();
					
					// [FS0002] - Verificar se arquivo APK inválido
					if(nomeItem.endsWith(".APK")){
											
						arquivoApk = item;
					}else{
						throw new ActionServletException("atencao.arquivo_apk_invalido");
					}
				}
				
			}
		
				
			FiltroSistemaAndroid filtroSistema = new FiltroSistemaAndroid();
			filtroSistema.adicionarParametro(new ParametroSimples(FiltroSistemaAndroid.ID, idSistemaAndroid));
			Collection<SistemaAndroid> colSistemasAndroid = fachada.pesquisar(filtroSistema, SistemaAndroid.class.getName());
			
			SistemaAndroid sistemaAndroid = (SistemaAndroid) Util.retonarObjetoDeColecao(colSistemasAndroid);
											
			// FS0003 - Versão existente
			FiltroVersaoSistemasAndroid filtro = new FiltroVersaoSistemasAndroid();
			
			filtro.adicionarParametro(
					new ParametroSimples(FiltroVersaoSistemasAndroid.VERSAO_NUMERO,
							versao));
			
			Collection<FiltroVersaoSistemasAndroid> colecaoVersaoMobile = 
				fachada.pesquisar(filtro, VersaoSistemasAndroid.class.getName());
			
			if(colecaoVersaoMobile!=null
					&& !colecaoVersaoMobile.isEmpty()){
				throw new ActionServletException("atencao.versao_ja_existe");
			}			
			
			
			//6.	O sistema armazena as informações lidas dos arquivos	
			VersaoSistemasAndroid versaoSistemasAndroidInserir = new VersaoSistemasAndroid();
			
			versaoSistemasAndroidInserir.setNumeroVersao(versao);

			versaoSistemasAndroidInserir.setSistemaAndroid(sistemaAndroid);
						
			versaoSistemasAndroidInserir.setDataEnvio(new Date());					
			
			versaoSistemasAndroidInserir.setArquivoApk(arquivoApk.get());					
			
			fachada.inserir(versaoSistemasAndroidInserir);					
			
		
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		montarPaginaSucesso(httpServletRequest, "Arquivos enviados com sucesso.",
				"Realizar outro upload.",
				"exibirUploadVersaoSistemasAndroidAction.do?menu=sim");
		
		return retorno;
		
	}
	
	public boolean validarArquivoApk(FormFile arquivoAnexo) {
		boolean arquivoValido = true;
		
		String arquivoExtensao = Util.obterExtensaoDoArquivo(arquivoAnexo.getFileName());
		
		// [FS0002] - Verificar se arquivo APK inválido
		if (!arquivoExtensao.equalsIgnoreCase("APK")){
			
			throw new ActionServletException("atencao.arquivo_apk_invalido");
		}
		
		return arquivoValido;
	}
}
