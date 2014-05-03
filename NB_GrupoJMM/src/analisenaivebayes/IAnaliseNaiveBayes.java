package analisenaivebayes;

import relacao.Atributo;
import relacao.Classe;
import relacao.Relacao;

public interface IAnaliseNaiveBayes {

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
	public double getProb_APrioriDeClasse(String nomeDaClasse);

	/**
	 * Calcula a probabilidade do atributo condicionado a classe.
	 * 
	 * @param atributo
	 * @param classe
	 * @return double
	 */
	public double getProb_Condicional(Atributo atributo, Classe classe);

	/**
	 * Calcula a probabilidade do atributo condicionado a classe.
	 * 
	 * @param nomeDeAtributo
	 * @param nomeDaClasse
	 * @return
	 */
	public double getProb_Condicional(String nomeDeAtributo,
			Object valorDeAtributo, String nomeDaClasse);

}
