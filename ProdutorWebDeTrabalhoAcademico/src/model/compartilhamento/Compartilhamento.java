package model.compartilhamento;

import model.usuario.Usuario;

public abstract class Compartilhamento {

	public static final Permissao PERMISSAO_APENAS_VISUALIZAR = new Permissao(
			0, "APENAS VISUALIZAR");
	public static final Permissao PERMISSAO_VISUALIZAR_E_COMENTAR = new Permissao(
			1, "VISUALIZAR E COMENTAR");
	public static final Permissao PERMISSAO_EDITAR = new Permissao(2, "EDITAR");

	private int id;
	private Usuario userCompartilhado;
	private Permissao permissao;

	public Compartilhamento() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUserCompartilhado() {
		return userCompartilhado;
	}

	public void setUserCompartilhado(Usuario userCompartilhado) {
		this.userCompartilhado = userCompartilhado;
	}

	protected Permissao getPermissao() {
		return permissao;
	}

	protected void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public static Permissao newPermissao(int int1) {

		if (int1 == PERMISSAO_APENAS_VISUALIZAR.getPermissao()) {
			return PERMISSAO_APENAS_VISUALIZAR;
		} else if (int1 == PERMISSAO_VISUALIZAR_E_COMENTAR.getPermissao()) {
			return PERMISSAO_VISUALIZAR_E_COMENTAR;
		} else if (int1 == PERMISSAO_EDITAR.getPermissao()) {
			return PERMISSAO_EDITAR;
		}

		return null;

	}

	public String permissaoToString() {

		return permissao.getValor();

	}
}

class Permissao {
	private int permissao;
	private String valor;

	protected Permissao(int permissao, String valor) {
		setPermissao(permissao);
		setValor(valor);
	}

	protected int getPermissao() {
		return permissao;
	}

	protected void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	protected String getValor() {
		return valor;
	}

	protected void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}
}
