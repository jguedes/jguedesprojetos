<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Cadastrar Usuário</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
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
			</tr>
		</table>
	</div>
	<div class="central">
		<div class="centralSuperior">
			<div class="divCentralTitulo">Cadastrar Usuário</div>
		</div>
		<form action="criarUsuario.jsp" method="post">
			<table class="criarUsuario_table">
				<tr>
					<td>Digite seu nome</td>
				</tr>
				<tr>
					<td><input class="txtNome" name="txtNome" type="text"></td>
				</tr>
				<tr>
					<td>Digite seu e-mail</td>
				</tr>
				<tr>
					<td><input class="txtEmail" name="txtEmail" type="text"></td>
				</tr>
				<tr>
					<td class="criarUsuario_linha"><hr></td>
				</tr>
				<tr>
					<td>Digite sua senha</td>
				</tr>
				<tr>
					<td><input class="txtSenha" name="txtSenha" type="password"></td>
				</tr>
				<tr>
					<td>Digite sua senha novamente</td>
				</tr>
				<tr>
					<td><input class="txtRepetirSenha" name="txtRepetirSenha"
						type="password"></td>
				</tr>
				<tr class="btnCriarUsuario">
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