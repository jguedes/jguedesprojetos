public class Usuario implements IUsuario {

	private String email;
	private String senha;

	@Override
	public void setEmail(String email) {

		this.email = email;

	}

	@Override
	public String getEmail() {

		return email;

	}

	@Override
	public void setSenha(String senha) {

		this.senha = senha;

	}

	@Override
	public String getSenha() {

		return senha;

	}

}
