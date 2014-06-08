package model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {

	private Connection conn;

	public DaoUsuario() {

	}

	public DaoUsuario(Connection conn) {

		this.conn = conn;

	}

	public boolean salvarUsuario(UsuarioAutor usuarioAutor) {

		String sql;

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			// INSERIR NOVO USUARIO
			sql = "insert into usuario (nome,email,senha) values (?,?,?);";

			ps = conn.prepareStatement(sql);

			ps.setString(1, usuarioAutor.getNome());

			ps.setString(2, usuarioAutor.getEmail());

			ps.setString(3, usuarioAutor.getSenha());

			ps.execute();

			// CONSULTAR USUARIO INSERIDO PARA PEGAR SEU ID

			sql = "SELECT LAST_INSERT_ID();";

			rs = ps.executeQuery(sql);

			rs.first();// -->vai para o primeiro registro do ResultSet

			usuarioAutor.setId(rs.getLong(1));// -->pegando na coluna 1

			rs.close();

			ps.close();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		} finally {

			try {

				if (rs != null) {

					if (!rs.isClosed()) {

						rs.close();

					}

				}

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	public boolean atualizarUsuario(UsuarioAutor usuario) {

		String sql;

		PreparedStatement ps = null;

		try {

			// INSERIR NOVO USUARIO
			sql = "update usuario set email = ?, senha = ? where nome = ?;";

			ps = conn.prepareStatement(sql);

			ps.setString(1, usuario.getEmail());

			ps.setString(2, usuario.getSenha());

			ps.setString(3, usuario.getNome());

			ps.execute();

			ps.close();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		} finally {

			try {

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * Se o usuario existir passa os dados nome e id por referencia.
	 * 
	 * @param user
	 * @return
	 */
	public boolean usuarioExiste(UsuarioAutor user) {

		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getEmail());

			ps.setString(2, user.getSenha());

			rs = ps.executeQuery();

			if (rs.first()) {

				user.setNome(rs.getString("nome"));

				user.setId(rs.getLong("id"));

				return true;

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		} finally {

			try {

				if (rs != null) {

					if (!rs.isClosed()) {

						rs.close();

					}

				}

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	public boolean emailExist(String email) {

		String sql = "SELECT * FROM usuario WHERE email = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.first()) {

				return true;

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		} finally {

			try {

				if (rs != null) {

					if (!rs.isClosed()) {

						rs.close();

					}

				}

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	public String getSenha(String email) {

		String sql = "SELECT * FROM usuario WHERE email = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.first()) {

				return rs.getString("senha");

			}

			return null;

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		} finally {

			try {

				if (rs != null) {

					if (!rs.isClosed()) {

						rs.close();

					}

				}

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * @param id_user
	 * @return Usuario
	 */
	public UsuarioAutor getUsuario(int id_user) {

		UsuarioAutor u;

		String sql = "SELECT * FROM usuario WHERE id = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_user);

			rs = ps.executeQuery();

			if (rs.first()) {

				u = new UsuarioAutor();

				u.setId(id_user);

				u.setNome(rs.getString("nome"));

				u.setEmail(rs.getString("email"));

				return u;

			}

			return null;

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		} finally {

			try {

				if (rs != null) {

					if (!rs.isClosed()) {

						rs.close();

					}

				}

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	public Usuario getUsuario(String email_user) {

		Usuario u;

		String sql = "SELECT * FROM usuario WHERE email = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, email_user);

			rs = ps.executeQuery();

			if (rs.first()) {

				u = new UsuarioAutor();

				u.setId(rs.getLong("id"));

				u.setNome(rs.getString("nome"));

				u.setEmail(email_user);

				return u;

			}

			return null;

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		} finally {

			try {

				if (rs != null) {

					if (!rs.isClosed()) {

						rs.close();

					}

				}

				if (ps != null) {

					if (!ps.isClosed()) {

						ps.close();

					}

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

}
