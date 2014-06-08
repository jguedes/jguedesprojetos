package model.compartilhamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.comentario.Comentario;
import model.comentario.DaoComentario;
import model.usuario.DaoUsuario;
import model.usuario.Usuario;

public class DaoCompartilhamento {

	private Connection conn;

	public DaoCompartilhamento() {

	}

	public DaoCompartilhamento(Connection conn) {

		this.conn = conn;

	}

	public void remover(int id) {

		String sql;

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			// deletar Compartilhamento
			sql = "delete from compartilhamento where id = ?;";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();

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

	public boolean salvarCompartilhamento(Compartilhamento compartilhamento,
			int id_doc) {

		String sql;

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			// INSERIR NOVO Compartilhamento
			sql = "insert into compartilhamento (id_documento,id_user_compartilhado,permissao) values (?,?,?);";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_doc);
			ps.setInt(2, (int) compartilhamento.getUserCompartilhado().getId());
			ps.setInt(3, compartilhamento.getPermissao().getPermissao());

			ps.execute();

			// CONSULTAR COMPARTILHAMENTO INSERIDO PARA PEGAR SEU ID

			sql = "SELECT LAST_INSERT_ID();";

			rs = ps.executeQuery(sql);

			rs.first();// -->vai para o primeiro registro do ResultSet

			compartilhamento.setId(rs.getInt(1));// -->pegando na coluna 1

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

	/**
	 * @param id_doc
	 * @return Uma lista de compartilhamentos de um documento.
	 */
	public List<Compartilhamento> listar(int id_doc) {

		DaoComentario daoComentario = null;

		DaoUsuario daoUsuario = null;

		List<Compartilhamento> lista = null;

		List<Comentario> comentarios = null;

		Compartilhamento c = null;

		Usuario userCompartilhado = null;

		PreparedStatement ps = null;

		ResultSet rs = null;

		String sql = "SELECT * FROM compartilhamento WHERE id_documento = ?";

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_doc);

			rs = ps.executeQuery();

			while (rs.next()) {

				c = compartilhamentoPorPermissao(rs.getInt("permissao"));

				c.setId(rs.getInt("id"));

				// pegar o usuario compartilhado

				daoUsuario = new DaoUsuario(conn);

				userCompartilhado = daoUsuario.getUsuario(rs
						.getInt("id_user_compartilhado"));

				c.setUserCompartilhado(userCompartilhado);

				// se for o caso pegar os comentarios

				if (c instanceof Compartilhamento_VisualizarEComentar) {

					if (daoComentario == null) {

						daoComentario = new DaoComentario(conn);

					}

					comentarios = daoComentario.getComentarios(c);

					((Compartilhamento_VisualizarEComentar) c)
							.setComentarios(comentarios);

				}

				if (lista == null) {

					lista = new ArrayList<Compartilhamento>();

				}

				lista.add(c);

			}

			return lista;

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

	public Compartilhamento getCompartilhamento(int id_doc,
			Usuario userCompartilhado) {

		Compartilhamento c = null;

		String sql = "SELECT * FROM compartilhamento WHERE id_documento = ? and id_user_compartilhado = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_doc);

			ps.setInt(2, (int) userCompartilhado.getId());

			rs = ps.executeQuery();

			if (rs.first()) {

				c = compartilhamentoPorPermissao(rs.getInt("permissao"));

				c.setId(rs.getInt("id"));

				c.setUserCompartilhado(userCompartilhado);

				// c.setDataCadastro(rs.getTimestamp("dataCadastro"));

			}

			return c;

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

	public static Compartilhamento compartilhamentoPorPermissao(int permissao) {
		switch (permissao) {
		case 0:
			return new Compartilhamento_ApenasVisualizar();
		case 1:
			return new Compartilhamento_VisualizarEComentar();
		case 2:
			return new Compartilhamento_Editar();
		default:
			return null;
		}
	}

}
