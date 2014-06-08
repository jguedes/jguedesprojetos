<%@page import="model.usuario.Usuario"%>
<%@page import="model.comentario.DaoComentario"%>
<%@page import="model.comentario.Comentario"%>
<%@page import="model.Conexao"%>
<%@page import="model.compartilhamento.DaoCompartilhamento"%>
<%@page import="model.documento.Documento"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PTAOL - Registrar Comentário</title>
</head>
<body>
	<%!Usuario comentarista;%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		if ((comentarista = (Usuario) session.getAttribute("user")) == null) {

			response.sendRedirect("index.jsp");

			return;

		}

		String elementoDeDocumento = request
				.getParameter("elementoDeDocumento");

		//buscar o compartilhamento e pegar o seu id

		int id_doc = Integer.parseInt(request.getParameter("id_doc"));

		int id_compartilhamento = new DaoCompartilhamento(Conexao.open())
				.getCompartilhamento(id_doc, comentarista).getId();

		Comentario comentario = new Comentario();

		comentario.setElementoDoDocumento(elementoDeDocumento);

		comentario.setTexto(request.getParameter("comentario"));

		new DaoComentario(Conexao.open()).salvar(comentario,
				id_compartilhamento);

		Conexao.close();

		response.sendRedirect("page_editarDocumento.jsp?id_doc=" + id_doc);
	%>
</body>
</html>