public class Leilao implements ILeilao {

	private double valor;
	private IUsuario usuario;
	private String descricao;

	@Override
	public void lance(double valor) throws Exception {

		this.valor += valor;

	}

	@Override
	public void finalizar(IUsuario usuario) {

		this.usuario = usuario;

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
	public double getValorAtual() {

		return valor;

	}

}
