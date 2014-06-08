<%@page import="model.documento.Documento"%>
<%@page import="java.util.List"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@page import="model.documento.DaoDocumento"%>
<%@page import="model.Conexao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Visualizar Documento</title>
</head>
<body>
	<%!UsuarioAutor user;
	Documento documento;%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		if ((user = (UsuarioAutor) session.getAttribute("user")) == null) {

			response.sendRedirect("index.jsp");

			return;

		} else {

			DaoDocumento dao = new DaoDocumento(Conexao.open());

			documento = dao.getDocumento(Integer.parseInt(request
					.getParameter("id_doc")));

			Conexao.close();

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
			<div class="divCentralTitulo">Visualizar Documento</div>
			<div class="divCentralBtns">
				<div class="btnCompartilhar">
					<!-- input type="button" value="Compartilhar" -->
				</div>

				<form action="documento.jsp?doc=<%=documento.getId()%>"
					method="post" name="form1">
					<table class="tblBtns">
						<tr>
							<td><input type="submit" name="btn" value="Novo"></td>
							<td><input type="submit" name="btn" value="Editar"></td>
							<td><input type="submit" name="btn" value="Excluir"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="visual">
			<div class="capa">
				<div class="instituicao"><%=documento.getInstituicao()%></div>
				<div class="titulo">
					<p><%=documento.getTitulo().toUpperCase()%></p>
				</div>
				<div class="autor"><%=user.getNome().toUpperCase()%></div>
				<div class="localdata"><%=documento.getLocaldata()%></div>
			</div>
			<div class="introducao"></div>
			<div class="desenvolvimento"></div>
			<div class="conclusao"></div>
		</div>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>