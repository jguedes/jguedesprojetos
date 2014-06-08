<%@page import="model.usuario.UsuarioAutor"%>
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
	<%!UsuarioAutor user;%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		user = (UsuarioAutor) session.getAttribute("user");

		if (user == null) {

			response.sendRedirect("index.jsp");

			return;

		}
	%>
	<div class="superior">
		<div class="slogan">
			<h1>PTAOL</h1>
			<h4>Produtor de Trabalhos Acadêmicos On Line</h4>
		</div>
		<div class="user">
			<div class="nome">
				<%=user.toString()%></div>
			<div>
				<table>
					<tr>
						<td><a href="sair.jsp">Sair</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="menu">
		<table>
			<tr>
				<td><a href="page_usuarioAutenticado.jsp">HOME</a></td>
			</tr>
		</table>
	</div>
	<div class="central">
		<div class="centralSuperior">
			<div class="divCentralTitulo">Alteração de Senha</div>
		</div>
		<form action="alterarSenha.jsp" method="post">
			<table class="criarUsuario_table">
				<tr>
					<td>Senha Atual</td>
				</tr>
				<tr>
					<td><input class="txtSenhaAtual" name="txtSenhaAtual"
						type="password"></td>
				</tr>
				<tr>
					<td>Nova Senha</td>
				</tr>
				<tr>
					<td><input class="txtSenha" name="txtSenha" type="password"></td>
				</tr>
				<tr>
					<td>Comfirmar Nova Senha</td>
				</tr>
				<tr>
					<td><input class="txtRepetirSenha" name="txtRepetirSenha"
						type="password"></td>
				</tr>
				<tr class="btnSalvar">
					<td><input type="submit" value="Salvar"></td>
				</tr>
			</table>

		</form>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>