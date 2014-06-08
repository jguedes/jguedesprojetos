package model.documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.compartilhamento.DaoCompartilhamento;

public class DaoDocumento {

	private Connection conn;

	public DaoDocumento() {

	}

	public DaoDocumento(Connection conn) {

		this.conn = conn;

	}

	public boolean salvarDocumento(Documento documento, int id_Autor) {

		String sql;

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			// INSERIR NOVO DOCUMENTO
			sql = "insert into documento (id_user_Dono,titulo) values (?,?);";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_Autor);

			ps.setString(2, documento.getTitulo());

			ps.execute();

			// CONSULTAR DOCUMENTO INSERIDO PARA PEGAR SEU ID

			sql = "SELECT LAST_INSERT_ID();";

			rs = ps.executeQuery(sql);

			rs.first();// -->vai para o primeiro registro do ResultSet

			documento.setId(rs.getInt(1));// -->pegando na coluna 1

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
	 * @param id_Autor
	 * @return uma lista de documentos para um Usuario Autor
	 */
	public List<Documento> listar(int id_Autor) {

		List<Documento> lista = null;

		DaoCompartilhamento daoCompartilhamento = null;

		String sql = "SELECT * FROM documento WHERE id_user_Dono = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_Autor);

			rs = ps.executeQuery();

			Documento d;

			while (rs.next()) {

				d = new Documento();

				d.setId(rs.getInt("id"));

				d.setTitulo(rs.getString("titulo"));

				d.setDataCadastro(rs.getTimestamp("dataCadastro"));

				if (daoCompartilhamento == null) {

					daoCompartilhamento = new DaoCompartilhamento(conn);

				}

				d.setCompartilhamentos(daoCompartilhamento.listar(d.getId()));

				if (lista == null) {

					lista = new ArrayList<Documento>();

				}

				lista.add(d);

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

	public Documento getDocumento(int id_doc) {

		DaoCompartilhamento daoCompartilhamento = null;

		PreparedStatement ps = null;

		ResultSet rs = null;

		Documento d = null;

		String sql = "SELECT * FROM documento WHERE id = ?";

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_doc);

			rs = ps.executeQuery();

			if (rs.first()) {

				d = new Documento();

				d.setId(id_doc);

				d.setInstituicao(rs.getString("instituicao"));

				d.setTitulo(rs.getString("titulo"));

				d.setLocaldata(rs.getString("localdata"));

				d.setIntroducao(rs.getString("introducao"));

				d.setDesenvolvimento(rs.getString("desenvolvimento"));

				d.setConclusao(rs.getString("conclusao"));

				d.setDataCadastro(rs.getTimestamp("dataCadastro"));

				daoCompartilhamento = new DaoCompartilhamento(conn);

				d.setCompartilhamentos(daoCompartilhamento.listar(d.getId()));

			}

			return d;

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

	public boolean update(Documento documento, String elementoDeDocumento) {

		String sql;

		PreparedStatement ps = null;

		try {

			switch (elementoDeDocumento) {

			case "capa":

				sql = "update documento set instituicao = ?, titulo = ?, localdata = ? where id = ?;";

				ps = conn.prepareStatement(sql);

				ps.setString(1, documento.getInstituicao());

				ps.setString(2, documento.getTitulo());

				ps.setString(3, documento.getLocaldata());

				ps.setInt(4, documento.getId());

				break;

			case "introducao":

				sql = "update documento set introducao = ? where id = ?;";

				ps = conn.prepareStatement(sql);

				ps.setString(1, documento.getIntroducao());

				ps.setInt(2, documento.getId());

				break;

			case "desenvolvimento":
				sql = "update documento set desenvolvimento = ? where id = ?;";

				ps = conn.prepareStatement(sql);

				ps.setString(1, documento.getDesenvolvimento());

				ps.setInt(2, documento.getId());

				break;

			case "conclusao":
				sql = "update documento set conclusao = ? where id = ?;";

				ps = conn.prepareStatement(sql);

				ps.setString(1, documento.getConclusao());

				ps.setInt(2, documento.getId());

				break;

			default:
				break;
			}

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

	public boolean excluir(int id_doc) {

		String sql;

		PreparedStatement ps = null;

		try {

			sql = "delete from documento where id = ?;";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, id_doc);

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
