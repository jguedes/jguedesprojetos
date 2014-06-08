<%@page import="model.usuario.UsuarioAutor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script -->
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Criar Documento</title>
</head>
<body>
	<%!UsuarioAutor user;%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		if ((user = (UsuarioAutor) session.getAttribute("user")) == null) {

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
						<td><a href="page_alterarSenha.jsp">Alterar Senha</a></td>
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
			<div class="divCentralTitulo">Criar Documento</div>
		</div>
		<form action="documento.jsp?id=criar" method="post">
			<table class="criarUsuario_table">
				<tr>
					<td>Digite o título do documento</td>
				</tr>
				<tr>
					<td><input class="txtTitulo" name="txtTitulo" type="text"></td>
				</tr>
				<tr class="btnCriarDocumento">
					<td><input type="submit" value="Criar"></td>
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