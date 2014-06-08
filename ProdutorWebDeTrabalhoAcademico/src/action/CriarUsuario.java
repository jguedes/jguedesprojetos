package action;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conexao;
import model.usuario.DaoUsuario;
import model.usuario.UsuarioAutor;

/**
 * Servlet implementation class CriarUsuario
 */
@WebServlet("/CriarUsuario")
public class CriarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String txtSenha = request.getParameter("txtSenha");

		String txtRepetirSenha = request.getParameter("txtRepetirSenha");

		if (txtSenha.equals(txtRepetirSenha)) {

			Connection conn = Conexao.open();

			DaoUsuario dao = new DaoUsuario(conn);

			UsuarioAutor user = new UsuarioAutor();

			String nome = request.getParameter("txtNome");

			String email = request.getParameter("txtEmail");

			user.setNome(nome);
			user.setEmail(email);
			user.setSenha(txtSenha);

			if (!dao.usuarioExiste(user)) {

				dao.salvarUsuario(user);

				request.getSession(true).setAttribute("user", user);

				response.sendRedirect("usuarioAutenticado.jsp");

				return;

			}

		}

		response.sendRedirect("criarUsuario.jsp");

	}

}
