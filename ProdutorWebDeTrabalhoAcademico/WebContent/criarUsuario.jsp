<%@page import="sendemailssl.SendMailSSL"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.internet.AddressException"%>
<%@page import="javax.mail.Transport"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@page import="model.usuario.DaoUsuario"%>
<%@page import="model.Conexao"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>Insert title here</title>
</head>
<body>
	<%!UsuarioAutor user;

	void enviarMensagem() {

		String From = "jqguedesneto@gmail.com";

		String To = user.getEmail();

		String subject = "PTAOL - Cadastro realizado";

		String body = user.getNome()
				+ " seu cadastro foir realizado com sucesso.\n\nSeu login: "
				+ user.getEmail() + "\nSua senha é: " + user.getSenha()
				+ "\n\nlink para o site: http://localhost:8080/PTAOL";

		SendMailSSL sendEmail = new SendMailSSL();

		sendEmail.sendMail(From, To, subject, body);
	}%>

	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String txtSenha = request.getParameter("txtSenha");

		String txtRepetirSenha = request.getParameter("txtRepetirSenha");

		if (txtSenha.equals(txtRepetirSenha)) {

			DaoUsuario dao = new DaoUsuario(Conexao.open());

			String nome = request.getParameter("txtNome");

			String email = request.getParameter("txtEmail");

			user = new UsuarioAutor();

			user.setNome(nome);

			user.setEmail(email);

			user.setSenha(txtSenha);

			if (dao.usuarioExiste(user)) {

				Conexao.close();

				response.sendRedirect("page_criarUsuario.jsp");

				return;

			} else {

				dao.salvarUsuario(user);

				Conexao.close();

				session.setAttribute("user", user);

				enviarMensagem();

			}

		} else {

			response.sendRedirect("page_criarUsuario.jsp");

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
				<td><a href="index.jsp">Principal</a></td>
				<td><a href="page_usuarioAutenticado.jsp">Entrar</a></td>
			</tr>
		</table>
	</div>
	<div class="central">
		<div class="centralSuperior">
			<div class="divCentralTitulo">Cadastrar Usuário</div>
		</div>
		<h1>
			<%=user.getNome()%>
			você foi cadastrado com sucesso!
		</h1>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>