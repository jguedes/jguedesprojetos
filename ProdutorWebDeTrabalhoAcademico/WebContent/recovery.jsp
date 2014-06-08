<%@page import="javax.websocket.SendResult"%>
<%@page import="sendemailssl.SendMailSSL"%>
<%@page import="org.apache.catalina.ha.tcp.SendMessageData"%>
<%@page import="model.DaoGenerico"%>
<%@page import="model.usuario.DaoUsuario"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Conexao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Relembrar Senha</title>
</head>
<body>
	<%!String txtEmail, senha;
	boolean ok;%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		txtEmail = request.getParameter("txtEmail");

		DaoUsuario dao = new DaoUsuario(Conexao.open());

		if (dao.emailExist(txtEmail)) {

			senha = dao.getSenha(txtEmail);

			Conexao.close();

			String From = "jqguedesneto@gmail.com";

			String To = txtEmail.toLowerCase();

			String subject = "Relebrar Senha";

			String body = "Sua senha é: " + senha;

			SendMailSSL sendEmail = new SendMailSSL();

			sendEmail.sendMail(From, To, subject, body);

			ok = true;

		} else {

			Conexao.close();

			ok = false;

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
				<td><a href="page_criarUsuario.jsp">Realizar Cadastro</a></td>
				<td><a href="page_recovery.jsp">Voltar</a></td>
			</tr>
		</table>
	</div>
	<div class="central">
		<div class="centralSuperior">
			<div class="divCentralTitulo">Relembrar Senha</div>
		</div>
		<%
			if (ok) {
		%>
		<h2>Você receberá uma messagem na sua caixa de e-mail contendo a
			sua senha.</h2>
		<br> <a href="index.jsp"> OK</a>
		<%
			} else {
		%>
		<h2>
			Email [
			<%=txtEmail%>
			] Não Cadastrado.
		</h2>
		<%
			}
		%>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>