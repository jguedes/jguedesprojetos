package relacao;

public class Atributo {

	private String nome;

	private Object valor;

	public Atributo() {

	}

	public Atributo(String nome, Object valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public Atributo(String nomeDeAtributo) {
		this(nomeDeAtributo, null);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
}
