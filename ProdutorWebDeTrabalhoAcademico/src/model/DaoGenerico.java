package model;

import java.sql.Connection;
import java.util.List;

public class DaoGenerico implements IDao {

	@Override
	public void setConexao(Connection conn) {
	}

	@Override
	public boolean salvar(String tabela, Object object) {

		return false;
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
