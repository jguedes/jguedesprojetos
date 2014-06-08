package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conexao;
import model.usuario.DaoUsuario;
import model.usuario.UsuarioAutor;

/**
 * Servlet implementation class AutenticarUsuario
 */
@WebServlet("/AutenticarUsuario")
public class AutenticarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutenticarUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UsuarioAutor user = new UsuarioAutor();

		user.setEmail(request.getParameter("txtEmail"));
		user.setSenha(request.getParameter("txtSenha"));

		DaoUsuario dao = new DaoUsuario(Conexao.open());
		
		if(dao.usuarioExiste(user)){
			
			Conexao.close();
			
			request.getSession(true).setAttribute("user", user);
			
			response.sendRedirect("usuarioAutenticado.jsp");
			
			return;
			
		}
		
		Conexao.close();
		
		

	}

}
