<%@page import="model.usuario.Usuario"%>
<%@page import="sendemailssl.SendMailSSL"%>
<%@page import="model.compartilhamento.DaoCompartilhamento"%>
<%@page import="model.compartilhamento.Compartilhamento"%>
<%@page import="model.Conexao"%>
<%@page import="model.usuario.DaoUsuario"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PTAOL - Compartilhamento</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		UsuarioAutor user;

		DaoCompartilhamento daoComp = new DaoCompartilhamento(
				Conexao.open());

		if ((user = (UsuarioAutor) session.getAttribute("user")) == null) {

			response.sendRedirect("index.jsp");

			return;

		}

		if (request.getParameter("permissao") != null) {

			String[] param = request.getParameter("permissao").split(":");

			int permissao = Integer.parseInt(param[0]);

			int id_doc = Integer.parseInt(param[1]);

			String tit_doc = param[2];

			String emailUserCompartilhado = request
					.getParameter("txtEmail");

			Usuario userCompartilhado = new DaoUsuario(Conexao.open())
					.getUsuario(emailUserCompartilhado);

			Compartilhamento c = DaoCompartilhamento
					.compartilhamentoPorPermissao(permissao);

			c.setUserCompartilhado(userCompartilhado);

			daoComp.salvarCompartilhamento(c, id_doc);

			if (!(request.getParameter("enviarEmail") != null)) {//.toString().equals("on")

				String subject = "PTAOL - " + user
						+ " compartilhou um documento com você";

				String body = user
						+ "está compartilhando o documento [ "
						+ tit_doc
						+ " ] com você. Você tem permissão para "
						+ c.permissaoToString()
						+ ". Faça login no site ou, se ainda não é cadastrado, cadastre-se para ver o documento: http://localhost:8080/PTAOL/"
						+ ".\n\nNão responder este e-mail.";

				new SendMailSSL()
						.sendMail("jqguedesneto@gmail.com",
								emailUserCompartilhado.toLowerCase(),
								subject, body);
			}

			Conexao.close();

			response.sendRedirect("page_compartilharDocumento.jsp?id_doc="
					+ id_doc);

			return;

		}

		if (request.getParameter("remover") != null) {

			daoComp.remover(Integer.parseInt(request
					.getParameter("remover").toString()));

			Conexao.close();

			response.sendRedirect("page_compartilharDocumento.jsp?id_doc="
					+ request.getParameter("id_doc"));

			return;

		}
	%>

</body>
</html>