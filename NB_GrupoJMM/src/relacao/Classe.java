package relacao;


public class Classe {

	private String nome;
	private double distribuicao;
	private long ocorrencias;

	public Classe() {

	}

	public Classe(String nome, long ocorrencias, double distribuicao) {
		this.nome = nome;
		this.ocorrencias = ocorrencias;
		this.distribuicao = distribuicao;
	}

	public Classe(String nomeDaClasse) {
		this(nomeDaClasse, 0, 0.0);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getNumOcorrencias() {
		return ocorrencias;
	}

	public double getDistribuicao() {
		return distribuicao;
	}

	public void setDistribuicao(double distribuicao) {
		this.distribuicao = distribuicao;
	}

	/**
	 * Adiciona mais uma ocorrência a classe.
	 */
	public void addOcorrencia() {

		ocorrencias++;

	}

}
