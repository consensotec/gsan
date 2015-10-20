package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.contratoadesao.ContratoAdesao;
import gcom.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1665] GerarContratoAdesao
 * @author Jonathan Marcos
 * @since 16/01/2015
 */
public class EmitirContratoAdesaoAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		
		HttpSession httpSession = httpServletRequest.getSession();
		
		if(httpSession.getAttribute("matricula")!=null && httpSession.getAttribute("idCliente")!=null
				&& httpSession.getAttribute("idRelacaoTipo")!=null){
			emitirContratoAdesao(httpServletResponse,
					new Integer(httpSession.getAttribute("matricula").toString()), 
					new Integer(httpSession.getAttribute("idCliente").toString()),
					new Integer(httpSession.getAttribute("idRelacaoTipo").toString()),
					true);
		}
		return null;
	}
	
	/**
	 * Método reponsável por<br>
	 * emitir contrato de adesão<br>
	 * esse método é chamado em<br>
	 * mais de uma funcionalidade
	 * @author Jonathan Marcos
	 * @since 16/01/2014
	 * @param httpServletResponse
	 * @param idImovel
	 * @param idCliente
	 */
	@SuppressWarnings({ "unchecked"})
	public void emitirContratoAdesao(HttpServletResponse httpServletResponse,Integer idImovel,Integer idCliente,
			Integer idTipoRelacao,boolean mensagemNaoAhContratoAdesaoMunicipio) {
		Fachada fachada = Fachada.getInstancia();
		
		Integer idControladorAdesao = fachada.pesquisarContratoAdesaoMunicipioAssociadoImovel(new Integer(idImovel));
		if(idControladorAdesao!=null){
			try{
				FiltroContratoAdesao filtroContratoAdesao = new FiltroContratoAdesao();
				filtroContratoAdesao.adicionarParametro(new ParametroSimples(FiltroContratoAdesao.ID, idControladorAdesao));
				ContratoAdesao contratoAdesao = (ContratoAdesao) Util.retonarObjetoDeColecao(fachada.
						pesquisar(filtroContratoAdesao, ContratoAdesao.class.getName()));
				File fileContratoAdesao = new File("contrato-adesao-original.pdf");
				FileOutputStream fileOutputStream = new FileOutputStream(fileContratoAdesao);
				fileOutputStream.write(contratoAdesao.getImagemArquivo());
				fileOutputStream.close();

				PDDocument pdfContratoAdesaoOriginal = PDDocument.load(fileContratoAdesao);
				PDDocument pdfContratoAdesaoModificado = new PDDocument();
					
				PDFont font = PDType1Font.TIMES_ROMAN;
					
				for(int posicaoPaginaAtual = 0;posicaoPaginaAtual<
						pdfContratoAdesaoOriginal.getDocumentCatalog().getAllPages().size();
							posicaoPaginaAtual++){
					if(posicaoPaginaAtual==0){
						PDPage primeiraPaginaPdfContratoAdesaoModificada = (PDPage) pdfContratoAdesaoOriginal.
								getDocumentCatalog().getAllPages().get(posicaoPaginaAtual);
						PDPageContentStream contentStream = new PDPageContentStream(pdfContratoAdesaoModificado, 
								primeiraPaginaPdfContratoAdesaoModificada,true,true);
						
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 40, 775);
						contentStream.drawString("INCRIÇÃO: " +fachada.pesquisarInscricaoImovel(new Integer(idImovel)));
						contentStream.endText();
						
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 450, 775);
						contentStream.drawString("MATRÍCULA: "+Util.retornaMatriculaImovelFormatada(idImovel));
						contentStream.endText();
						
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
						
						Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(
								fachada.pesquisar(filtroCliente, Cliente.class.getName()));
					
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 39, 760);
						contentStream.drawString("CLIENTE: "+cliente.getNome());
						contentStream.endText();
						
						String cpfOUCnpj = "";
						if(cliente.getCpf()!=null || cliente.getCnpj()!=null){
							if(cliente.getCpf()!=null){
								cpfOUCnpj = Util.formatarCpf(cliente.getCpf());
							}else{
								cpfOUCnpj = Util.formatarCnpj(cliente.getCnpj());
							}
						}
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 39, 745);
						contentStream.drawString("CPF/CNPJ: "+cpfOUCnpj);
						contentStream.endText();
						
						String rg = cliente.getRg()!=null ? cliente.getRg() : "";
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 450, 745);
						contentStream.drawString("RG: "+rg);
						contentStream.endText();
						
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 39, 730);
						contentStream.drawString("ENDEREÇO: "+fachada.pesquisarEnderecoFormatado(new Integer(idImovel)));
						contentStream.endText();
						
						FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
						filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.
								CLIENTE_RELACAO_TIPO_ID, idTipoRelacao));
						
						ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroClienteRelacaoTipo,
								ClienteRelacaoTipo.class.getName()));
						
						String descricaoRelacaoTipo = clienteRelacaoTipo!=null ? clienteRelacaoTipo.getDescricao() : "";
						
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 39, 715);
						contentStream.drawString("TIPO DE VÍNCULO: "+descricaoRelacaoTipo);
						contentStream.endText();
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, idCliente));
						filtroClienteImovel.adicionarParametro(new ParametroNulo("dataFimRelacao"));
						
						ClienteImovel clienteImovel = (ClienteImovel)Util.retonarObjetoDeColecao(fachada.pesquisar(
								filtroClienteImovel, ClienteImovel.class.getName()));
						
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 39, 700);
						contentStream.drawString("DATA DE INÍCIO DA VINCULAÇÃO DO CLIENTE AO IMÓVEL: "+simpleDateFormat.format(clienteImovel.getDataInicioRelacao()));
						contentStream.endText();
						
						contentStream.beginText();
						contentStream.setFont( font, 8 );
						contentStream.moveTextPositionByAmount( 39, 685);
						contentStream.drawString("DATA DE EMISSÃO: "+simpleDateFormat.format(new Date()));
						contentStream.endText();
						
						contentStream.close();
						
						pdfContratoAdesaoModificado.addPage(primeiraPaginaPdfContratoAdesaoModificada);
					}else{
							pdfContratoAdesaoModificado.addPage((PDPage)pdfContratoAdesaoOriginal.
									getDocumentCatalog().getAllPages().get(posicaoPaginaAtual));
					}
				}
				
				File fileContratoAdesaoModificado = new File("contrato-adesao-modificado.pdf"); 
				pdfContratoAdesaoModificado.save(fileContratoAdesaoModificado);
				FileInputStream fileInputStream = new FileInputStream(fileContratoAdesaoModificado);
				httpServletResponse.setContentType("application/pdf");
				httpServletResponse.setHeader("Content-Disposition",
						"attachment;filename=\"Contrato de Adesão.pdf\"");
				int bytes = fileInputStream.read();
				ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
				while(bytes!=-1){
					servletOutputStream.write(bytes);
					bytes = fileInputStream.read();
				}
				fileInputStream.close();
				fileOutputStream.close();
				servletOutputStream.flush();
				servletOutputStream.close();
				pdfContratoAdesaoOriginal.close();
				pdfContratoAdesaoModificado.close();
			}catch (Exception e) {
				throw new ActionServletException(
						"atencao.erro.ao.emitir.contrato.adesao");
			}
				
		}else{
			if(mensagemNaoAhContratoAdesaoMunicipio){
				throw new ActionServletException(
						"atencao.municipio.sem.contrato.adesao");
			}
		}
	}
}
