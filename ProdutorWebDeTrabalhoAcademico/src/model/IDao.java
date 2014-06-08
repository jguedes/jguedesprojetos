package model;

import java.sql.Connection;
import java.util.List;

public interface IDao {

	public void setConexao(Connection conn);

	public boolean salvar(String tabela,Object object);

	public List<Object> listar();

}
