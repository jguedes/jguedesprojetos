package analisederelacao;

import relacao.Classe;
import relacao.Relacao;

public interface IAnaliseDeRelacao {

	public void setRelacao(Relacao relacao);

	/**
	 * Calcula a probabilidade a priori da classe no parâmetro.
	 * 
	 * @param classe
	 * @return
	 */
	public double getProb_APrioriDeClasse(Classe classe);

	/**
	 * Calcula a probabilidade a priori da classe com o nome do parâmetro.
	 * 
	 * @param nomeDaClasse
	 * @return
	 */
	double getProb_APrioriDeClasse(String nomeDaClasse);

}
