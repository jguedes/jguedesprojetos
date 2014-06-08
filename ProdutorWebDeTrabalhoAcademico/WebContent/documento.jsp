<%@page import="model.compartilhamento.Compartilhamento_Editar"%>
<%@page import="model.compartilhamento.Compartilhamento"%>
<%@page import="model.compartilhamento.DaoCompartilhamento"%>
<%@page import="model.comentario.DaoComentario"%>
<%@page import="model.usuario.UsuarioAutor"%>
<%@page import="model.Conexao"%>
<%@page import="model.documento.DaoDocumento"%>
<%@page import="model.documento.Documento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PTAOL - Documentos</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		DaoDocumento dao = null;
		DaoCompartilhamento daoCompartilhamento = null;
		DaoComentario daoComentario = null;
		Documento documento = null;
		Compartilhamento_Editar compartilhamento;
		UsuarioAutor autor;
		String btnClicado, doc, titulo, id, elementoDeDocumento;
		int id_doc = 0, id_comentario;

		dao = new DaoDocumento(Conexao.open());
		doc = request.getParameter("id_doc");
		elementoDeDocumento = request.getParameter("elementoDeDocumento");
		if (doc != null) {
			id_doc = Integer.parseInt(doc);
		}

		if ((btnClicado = request.getParameter("btn")) != null) {

			if (btnClicado.equals("Novo")) {

				response.sendRedirect("page_criarDocumento.jsp");

				return;

			}

			if (btnClicado.equals("Atualizar")) {

				documento = dao.getDocumento(id_doc);

				if (elementoDeDocumento.equals("capa")) {

					documento.setInstituicao(request
							.getParameter("instituicao"));

					documento.setTitulo(request.getParameter("titulo"));

					documento.setLocaldata(request
							.getParameter("localdata"));

// 					return;

				} else if (elementoDeDocumento.equals("introducao")) {

					documento.setIntroducao(request
							.getParameter("introducao"));

				} else if (elementoDeDocumento.equals("desenvolvimento")) {

					documento.setDesenvolvimento(request
							.getParameter("desenvolvimento"));

				} else if (elementoDeDocumento.equals("conclusao")) {

					documento.setConclusao(request
							.getParameter("conclusao"));

				}

				dao.update(documento, elementoDeDocumento);

				Conexao.close();

				response.sendRedirect("page_editarDocumento.jsp?id_doc="
						+ id_doc);

				return;

			}

			if ((doc = request.getParameter("doc")) != null) {

				id_doc = Integer.parseInt(doc);

				if (btnClicado.equals("Compartilhar")) {

					response.sendRedirect("page_compartilharDocumento.jsp?id_doc="
							+ id_doc);

					return;

				} else if (btnClicado.equals("Editar")) {

					response.sendRedirect("page_editarDocumento.jsp?id_doc="
							+ id_doc);

					return;

				} else if (btnClicado.equals("Visualizar")) {

					response.sendRedirect("page_visualizarDocumento.jsp?id_doc="
							+ id_doc);

					return;

				} else if (btnClicado.equals("Excluir")) {

					dao = new DaoDocumento(Conexao.open());

					titulo = dao.getDocumento(id_doc).getTitulo();

					Conexao.close();

					response.sendRedirect("page_excluirDocumento.jsp?id_doc="
							+ id_doc + "&titulo=" + titulo);

					return;

				}
			} else {

				response.sendRedirect("page_usuarioAutenticado.jsp");

				return;
			}
		}

		if ((id = request.getParameter("id")) != null) {

			if (id.equals("criar")) {

				titulo = request.getParameter("txtTitulo").toString();

				documento = new Documento();

				documento.setTitulo(titulo);

				autor = (UsuarioAutor) session.getAttribute("user");

				if (dao.salvarDocumento(documento, (int) autor.getId())) {

					//criar auto-compartilhamento
					daoCompartilhamento = new DaoCompartilhamento(
							Conexao.open());

					compartilhamento = new Compartilhamento_Editar();

					compartilhamento.setUserCompartilhado(autor);

					if (daoCompartilhamento.salvarCompartilhamento(
							compartilhamento, documento.getId())) {

						System.out.print("Ok Documento " + documento
								+ " criado! id: " + documento.getId());

					}

					Conexao.close();

					response.sendRedirect("page_usuarioAutenticado.jsp");

					return;

				}

				Conexao.close();

				response.sendRedirect("page_criarDocumento.jsp?id=false");

				return;

			} else if (id.equals("remover")) {

				id_doc = Integer.parseInt(request.getParameter("id_doc"));

				dao = new DaoDocumento(Conexao.open());

				if (dao.excluir(id_doc)) {

					System.out
							.print("Ok Documento excluído com sucesso! id: "
									+ id_doc);

					Conexao.close();

					response.sendRedirect("page_usuarioAutenticado.jsp");

					return;
				}

			} else if (id.equals("comResolvido")) {

				id_comentario = Integer.parseInt(request
						.getParameter("id_comentario"));

				if (daoComentario.marcarComoResolvido(id_comentario)) {

					System.out
							.print("Ok Comentário marcado como resolvido com sucesso! id: "
									+ id_comentario);

					Conexao.close();

					response.sendRedirect("page_editarDocumento.jsp?elementoDeDocumento="
							+ elementoDeDocumento);

					return;
				}

			}
		}
	%>
</body>
</html>