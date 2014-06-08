<%@page
	import="model.compartilhamento.Compartilhamento_VisualizarEComentar"%>
<%@page import="model.compartilhamento.Compartilhamento"%>
<%@page import="model.comentario.Comentario"%>
<%@page import="model.documento.DaoDocumento"%>
<%@page import="model.documento.Documento"%>
<%@page import="java.util.ArrayList"%>
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
<title>PTAOL - Editar Documento</title>
</head>
<!-- a href="minhapagina.jsp?id=<=--//meuobj.getId()%>>"></a -->
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

		if (((id_doc = request.getParameter("id_doc")) == null)) {

			response.sendRedirect("page_usuarioAutenticado.jsp");

			return;

		}

		String elementoDeDocumento;

		if ((elementoDeDocumento = request
				.getParameter("elementoDeDocumento")) == null) {

			elementoDeDocumento = "capa";

		}

		documento = new DaoDocumento(Conexao.open()).getDocumento(Integer
				.parseInt(id_doc));

		Conexao.close();

		List<Compartilhamento> comps = documento.getCompartilhamentos();

		Compartilhamento_VisualizarEComentar cVE;

		List<Comentario> comen = null;

		for (Compartilhamento c : comps) {

			if (c instanceof Compartilhamento_VisualizarEComentar) {

				cVE = (Compartilhamento_VisualizarEComentar) c;

				if (cVE.getComentarios(elementoDeDocumento) != null) {

					comen = cVE.getComentarios(elementoDeDocumento);

				}

			}

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
			<div class="divCentralTitulo">Editar de Documento</div>
			<div class="divCentralBtns">
				<div class="btnCompartilhar">
					<!-- input type="button" value="Compartilhar" -->
				</div>
				<form action="documento.jsp?doc=<%=id_doc%>" method="post"
					name="form1">
					<table class="tblBtns">
						<tr>
							<td><input type="submit" name="btn" value="Novo"></td>
							<!-- td><input type="submit" name="btn"
								value="Salvar modificações"></td -->
							<td><input type="submit" name="btn" value="Visualizar"></td>
							<td><input type="submit" name="btn" value="Excluir"></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="divCentralTitDoc"><%=documento.getTitulo().toUpperCase()%></div>
		</div>
		<div class="centralIntermediaria">
			<div class="centralIntermediaria_left">
				<ul>
					<li><a
						href="page_editarDocumento.jsp?elementoDeDocumento=capa&id_doc=<%=id_doc%>">Capa</a></li>
					<li><a
						href="page_editarDocumento.jsp?elementoDeDocumento=introducao&id_doc=<%=id_doc%>">Introdução</a></li>
					<li><a
						href="page_editarDocumento.jsp?elementoDeDocumento=desenvolvimento&id_doc=<%=id_doc%>">Desenvolvimento</a></li>
					<li><a
						href="page_editarDocumento.jsp?elementoDeDocumento=conclusao&id_doc=<%=id_doc%>">Conclusão</a></li>
				</ul>

			</div>
			<div class="centralIntermediaria_right">
				<form action="documento.jsp?" name="form2" method="get">
					<input value="<%=id_doc%>" type="hidden" name="id_doc"> <input
						value="<%=elementoDeDocumento%>" type="hidden"
						name="elementoDeDocumento">

					<div class="edicao">
						<%
							if (elementoDeDocumento.equals("capa")) {
						%>
						<div class="capa">
							<div class="instituicao">
								Instituição:<input
									value="<%=documento.getInstituicao().toUpperCase()%>"
									type="text" name="instituicao">
							</div>
							<div class="titulo">
								Título: <input value="<%=documento.getTitulo().toUpperCase()%>"
									type="text" name="titulo">
							</div>
							<div class="autor">
								Autor: <input value="<%=user.getNome().toUpperCase()%>"
									type="text" name="autor">
							</div>
							<div class="localdata">
								Local e data:<input value="<%=documento.getLocaldata()%>"
									type="text" name="localdata">
							</div>
						</div>
						<%
							} else if (elementoDeDocumento.equals("introducao")) {
						%>
						<div class="introducao">
							<textarea rows="" cols="" name="introducao"><%=documento.getIntroducao()%></textarea>
						</div>
						<%
							} else if (elementoDeDocumento.equals("desenvolvimento")) {
						%>
						<div class="desenvolvimento">
							<textarea rows="" cols="" name="desenvolvimento"><%=documento.getDesenvolvimento()%></textarea>
						</div>
						<%
							} else if (elementoDeDocumento.equals("conclusao")) {
						%>
						<div class="conclusao">
							<textarea rows="" cols="" name="conclusao"><%=documento.getConclusao()%></textarea>
						</div>
						<%
							}
						%>

					</div>
					<input type="submit" name="btn" value="Atualizar">
				</form>
			</div>
		</div>
		<div class="centralInferior">
			<p>Histórico de comentários</p>
			<table>
				<%
					if (comen != null) {
						for (Comentario comentario : comen) {
				%>
				<tr>
					<td><%=comentario.getTexto()%></td>
					<%
						if (!comentario.isResolvido()) {
					%>
					<td><a
						href="documento.jsp?id=comResolvido&id_comentario=<%=comentario.getId()%>&itemDocumento=<%=elementoDeDocumento%>">Resolvido</a></td>
					<%
						}
					%>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td>Sem comentários para <%=elementoDeDocumento%>.
					</td>
				</tr>

				<%
					}
				%>
			</table>

			<form
				action="registrarComentario.jsp?elementoDeDocumento=<%=elementoDeDocumento + "&id_doc=" + id_doc%>"
				method="post">
				<div>
					<textarea name="comentario" rows="5" cols="100"></textarea>
					<input type="submit" value="Registrar">
				</div>
			</form>

		</div>
	</div>
	<div class="inferior">
		<a class="makeby" href="https://code.google.com/p/jguedesprojetos">Produzido
			por JGuedesProjetos</a>
	</div>
</body>
</html>