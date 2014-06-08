<%@page import="utils.Utils"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.documento.DaoDocumento"%>
<%@page import="model.documento.Documento"%>
<%@page import="java.util.List"%>
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
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<link rel="shortcut icon" href="./imagens/icon.jpg">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>PTAOL - Home</title>
</head>
<body>
	<%!UsuarioAutor user;
	List<Documento> documentos;%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		if ((user = (UsuarioAutor) session.getAttribute("user")) == null) {

			response.sendRedirect("index.jsp");

			return;

		} else {

			DaoDocumento dao = new DaoDocumento(Conexao.open());

			documentos = dao.listar((int) user.getId());

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
			<div class="divCentralTitulo">Lista de Documentos</div>
		</div>
		<div class="divCentralBtns">
			<div class="btnCompartilhar">
				<form action="documento.jsp" method="post" name="form1">
					<input class="btnCompart" type="submit" value="Compartilhar"
						name="btn" <%=(documentos != null) ? "" : "disabled"%>>
					<!--div class="tblBtns" -->
					<table class="tblBtns">
						<tr>
							<td><input type="submit" name="btn" value="Novo"></td>
							<td><input type="submit" name="btn" value="Editar"
								<%=(documentos != null) ? "" : "disabled"%>></td>
							<td><input type="submit" name="btn" value="Visualizar"
								<%=(documentos != null) ? "" : "disabled"%>></td>
							<td><input type="submit" name="btn" value="Excluir"
								<%=(documentos != null) ? "" : "disabled"%>></td>
						</tr>
					</table>
					<!-- /div -->
					<table class="listaDocs" border="1">
						<tr class="tr_nameColmns">
							<td class="td_selecao">#</td>
							<td class="td_nome">Nome</td>
							<td class="td_data">Data</td>
							<td class="td_compartilhado">Compartilhado</td>
						</tr>
						<%
							if (documentos != null) {
								int i = 0;
								for (Documento d : documentos) {
						%>
						<tr class=<%="tr_" + (i % 2 == 0 ? "par" : "impar")%>>
							<td class="td_selecao"><input type="radio" name="doc"
								value="<%=d.getId()%>"></td>
							<td><%=d.getTitulo()%></td>
							<td><%=Utils.data(d.getDataCadastro().getTime())%></td>
							<td class="td_compartilhado"><%=d.isCompartilhado() ? "SIM" : "NÃO"%></td>
						</tr>
						<%
							i++;
								}
							}
						%>
					</table>
				</form>
			</div>
		</div>
	</div>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>