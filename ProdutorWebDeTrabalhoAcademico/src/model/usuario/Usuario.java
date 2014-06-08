package model.usuario;

public class Usuario {

	protected long id;
	protected String nome;
	protected String email;

	public Usuario() {
		super();
	}

	public void setId(long id) {

		this.id = id;

	}

	public long getId() {

		return id;

	}

	public String getNome() {

		return nome;

	}

	public void setNome(String nome) {

		this.nome = nome.toUpperCase();

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email.toLowerCase();

	}

}