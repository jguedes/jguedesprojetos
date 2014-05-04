<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Produtor de Trabalhos Acadêmicos On Line</title>
</head>
<body>
	<div class="superior">
		<div class="slogan">
			<h1>PTAOL</h1>
			<h4>Produtor de Trabalhos Acadêmicos On Line</h4>
		</div>
		<div class="user">
			<form action="">
				<table class="index_table">
					<tr>
						<td>E-mail</td>
						<td>Senha</td>
						<td></td>
					</tr>
					<tr>
						<td><input class="txtEmail" type="text"></td>
						<td><input class="txtSenha" type="password"></td>
						<td><input class="btnAutenticar" type="submit"
							value="Autenticar"></td>
					</tr>
					<tr>
						<td colspan="3">
							<ul>
								<li><a href="criarUsuario.jsp">criar usuário</a></li>
								<li><a href="recovery.jsp">esqueceu a senha?</a></li>
							</ul>
						</td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<hr>
	</div>
	<div class="central"></div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>