<%@page import="sendemailssl.SendMailSSL"%>
<%@page import="model.Conexao"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@page import="model.usuario.DaoUsuario"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Alteração de Senha</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		DaoUsuario dao = null;

		UsuarioAutor user = (UsuarioAutor) session.getAttribute("user");

		if (user == null) {

			response.sendRedirect("index.jsp");

			return;

		}

		String txtSenhaAtual = request.getParameter("txtSenhaAtual");

		if (!user.getSenha().equals(txtSenhaAtual)) {

			response.sendRedirect("page_alterarSenha.jsp");

			return;

		}

		String txtSenha = request.getParameter("txtSenha");

		String txtRepetirSenha = request.getParameter("txtRepetirSenha");

		if (txtSenha.equals(txtRepetirSenha)) {

			dao = new DaoUsuario(Conexao.open());

			user.setSenha(txtSenha);

			if (dao.atualizarUsuario(user)) {

				Conexao.close();

				session.setAttribute("user", user);

				String From = "jqguedesneto@gmail.com";

				String To = user.getEmail();

				String subject = "PTAOL - Alteração de Senha";

				String body = "Você alterou a sua senha com sucesso. Sua nova senha é: "
						+ user.getSenha();

				SendMailSSL sendEmail = new SendMailSSL();

				sendEmail.sendMail(From, To, subject, body);

			}

		} else {

			response.sendRedirect("page_alterarSenha.jsp");

			return;

		}
	%>
	<div class="superior">
		<div class="slogan">
			<h1>PTAOL</h1>
			<h4>Produtor de Trabalhos Acadêmicos On Line</h4>
		</div>
		<div class="user"></div>
	</div>
	<div class="menu">
		<table>
			<tr>
				<td><a href="page_usuarioAutenticado.jsp">Ir para minha
						página principal</a></td>
				<td><a href="sair.jsp">Sair</a></td>
			</tr>
		</table>
	</div>
	<div class="central">
		<div class="centralSuperior">
			<div class="divCentralTitulo">Alteração de Senha</div>
		</div>
		<h2>Senha alterada com sucesso! Você receberá uma messagem na sua
			caixa de e-mail contendo a sua nova senha.</h2>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>