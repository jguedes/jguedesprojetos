<%@page import="model.Conexao"%>
<%@page import="model.usuario.DaoUsuario"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PTAOL - Autenticar Usuário</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		UsuarioAutor user = new UsuarioAutor();

		user.setEmail(request.getParameter("txtEmail"));
		user.setSenha(request.getParameter("txtSenha"));

		DaoUsuario dao = new DaoUsuario(Conexao.open());

		if (dao.usuarioExiste(user)) {

			Conexao.close();

			session.setAttribute("user", user);

			response.sendRedirect("page_usuarioAutenticado.jsp");

			return;

		}

		Conexao.close();

		response.sendRedirect("index.jsp");
	%>

</body>
</html>