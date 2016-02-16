<%@page import="gcom.gui.mobile.execucaoordemservico.ImagemOSCobranca"%>
<%@ page import = "java.io.*" %>

<%
	OutputStream o = null;
	try{
//		out.clear();
//		out.clearBuffer();
		Integer idArquivo = Integer.parseInt(request.getParameter("idArquivo")) ;
		Integer idOrdemServico = Integer.parseInt(request.getParameter("idOrdemServico")) ;
		Integer idSituacao = Integer.parseInt(request.getParameter("idSituacao")) ;
		
		byte[] imgData = ImagemOSCobranca.getImagem(idArquivo, idOrdemServico, idSituacao);  
		
		// display the image
		o = response.getOutputStream();
		response.setContentType("image/jpg");
		o.write(imgData);
	}catch (Exception e)
    {
	      e.printStackTrace();
    }finally{
		o.flush();
		o.close();
	}
  
%>
