<%@ page import = "java.io.*" %>

<%
	OutputStream o = null;
	try{
		out.clear();
		out.clearBuffer();
		Integer idFoto = Integer.parseInt(request.getParameter("idArquivo"));
		byte[] imgData = gcom.gui.atendimentopublico.ordemservico.ImagemFiscalizacaoFoto.getImagem(idFoto);
		
		// display the image
		
		response.setContentType("image/jpg");
		o = response.getOutputStream();
		o.write(imgData);
	}catch (Exception e)
    {
	      e.printStackTrace();
    }finally{
		o.flush();
		o.close();
	}
  
%>