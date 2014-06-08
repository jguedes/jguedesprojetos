<%@page import="utils.Utils"%>
<%@page import="model.compartilhamento.Compartilhamento"%>
<%@page import="model.documento.DaoDocumento"%>
<%@page import="model.documento.Documento"%>
<%@page import="model.usuario.DaoUsuario"%>
<%@page import="model.Conexao"%>
<%@page import="java.sql.Connection"%>
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
<title>PTAOL - Compartilhar Documento</title>
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

		}

		String id_doc;

		if ((id_doc = request.getParameter("id_doc")) == null) {

			response.sendRedirect("page_usuarioAutenticado.jsp");

			return;

		}

		documento = new DaoDocumento(Conexao.open()).getDocumento(Integer
				.parseInt(id_doc));
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
						<td><a href="alterarSenha.jsp">Alterar Senha</a></td>
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
			<div class="divCentralTitulo">
				Compartilhar<br>
			</div>

			<br> <br> DOCUMENTO ATUAL:
			<%=documento + ", "
					+ Utils.data(documento.getDataCadastro().getTime()) + ", "
					+ user%>
		</div>
		<div class="centralGrid">
			<%
				if (documento.isCompartilhado()) {
			%>
			<table border="1">
				<tr class="tr_nameColmns">
					<td class="td_nome">Nome</td>
					<td class="td_compartilhado">Tipo</td>
					<td>Remover</td>
				</tr>
				<%
					int i = 1;
						for (Compartilhamento c : documento.getCompartilhamentos()) {
							if (c.getUserCompartilhado().getNome()
									.equals(user.getNome()))
								continue;
				%>
				<tr class=<%="tr_" + (i % 2 == 0 ? "par" : "impar")%>>
					<td><%=c.getUserCompartilhado().getNome()%></td>
					<td><%=c.permissaoToString()%></td>
					<td><a
						href="compartilhamento.jsp?remover=
						<%=c.getId()%>&id_doc=<%=documento.getId()%>">Remover</a></td>
				</tr>
				<%
					i++;
						}
				%>
			</table>
			<%
				}
			%>
			<div>

				<form action="compartilhamento.jsp">
					<table>
						<tr>
							<td>Email</td>
							<td>Permitir</td>
						</tr>
						<tr>
							<td><input type="text" name="txtEmail"></td>
							<td><select name="permissao">
									<option value="0:<%=id_doc + ":" + documento.getTitulo()%>">APENAS
										VISUALIZAR</option>
									<option value="1:<%=id_doc + ":" + documento.getTitulo()%>">VISUALIZAR
										E COMENTAR</option>
									<option value="2:<%=id_doc + ":" + documento.getTitulo()%>">EDITAR</option>
							</select></td>
							<td><input type="submit" value="Compartilhar"></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td><input type="checkbox" name="enviarEmail">Enviar
								por Email</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>