public class Produto implements IProduto {

	private String nome;
	private String descricao;
	private String categoria;

	@Override
	public void setNome(String nome) {

		this.nome = nome;

	}

	@Override
	public String getNome() {

		return this.nome;

	}

	@Override
	public void setDescricao(String descricao) {

		this.descricao = descricao;

	}

	@Override
	public String getDescricao() {

		return descricao;

	}

	@Override
	public void setCategoria(String categoria) {

		this.categoria = categoria;

	}

	@Override
	public String getCategoria() {

		return categoria;

	}

}
