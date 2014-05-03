package analisenaivebayes;

/**
 * São os dados de ocorrências e distribuição de uma classe.
 * 
 * @author joao guedes
 */
public class DadosDeClasse {

	private long ocorrencias;

	private double distribuicao;

	public long getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(long ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public double getDistribuicao() {
		return distribuicao;
	}

	public void setDistribuicao(double distribuicao) {
		this.distribuicao = distribuicao;
	}

}
