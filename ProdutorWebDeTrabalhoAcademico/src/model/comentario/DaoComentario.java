package model.comentario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.compartilhamento.Compartilhamento;

public class DaoComentario {

	private Connection conn;

	public DaoComentario(Connection conn) {

		this.conn = conn;

	}

	public boolean salvar(Comentario comentario, int id_compartilhamento) {

		String sql;

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			// INSERIR NOVO Comentario
			sql = "insert into comentario (id_compartilhamento,elementoDeDocumento,texto) values (?,?,?);";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_compartilhamento);

			ps.setString(2, comentario.getElementoDeDocumento());

			ps.setString(3, comentario.getTexto());

			ps.execute();

			// CONSULTAR Comentario INSERIDO PARA PEGAR SEU ID

			sql = "SELECT LAST_INSERT_ID();";

			rs = ps.executeQuery(sql);

			rs.first();// -->vai para o primeiro registro do ResultSet

			comentario.setId(rs.getInt(1));// -->pegando na coluna 1

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

	public List<Comentario> getComentarios(Compartilhamento compartilhamento) {

		List<Comentario> comentarios = null;

		Comentario c = null;

		String sql = "select * from comentario where id_compartilhamento = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, compartilhamento.getId());

			rs = ps.executeQuery();

			while (rs.next()) {

				if (comentarios == null)
					comentarios = new ArrayList<Comentario>();

				c = new Comentario();

				// c.setComentarista(compartilhamento.getUserCompartilhado()
				// .getNome());

				c.setDataPostagem(rs.getTimestamp("dataPostagem"));

				if (rs.getTimestamp("dataResolvido") != null) {

					c.setDataResolvido(rs.getTimestamp("dataResolvido"));

					c.setResolvido(true);

				} else {

					c.setResolvido(false);

				}
				c.setId(rs.getInt("id"));

				c.setElementoDoDocumento(rs.getString("elementoDeDocumento"));

				c.setTexto(rs.getString("texto"));

				comentarios.add(c);

			}

			return comentarios;

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

	public boolean marcarComoResolvido(int id_comentario) {

		String sql = "update comentario set dataResolvido = ? where id = ?";

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setTimestamp(1, new Timestamp(new Date().getTime()));

			ps.setInt(2, id_comentario);

			ps.execute();
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

	public boolean excluir(int id_Comentario) {

		String sql;

		PreparedStatement ps = null;

		try {

			sql = "delete from comentario where id = ?;";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_Comentario);

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

}
