package relacao;

public class Classe {

	private String nome;

	public Classe() {

	}

	public Classe(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {

		Classe c = (Classe) obj;

		if (this.nome.equalsIgnoreCase(c.getNome())) {

			return true;

		}

		return false;

	}

}
