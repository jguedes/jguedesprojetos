package montadorderelacao;

import relacao.Relacao;
import analisadordearquivo.IAnalisadorDeArquivo;

public interface IMontadorDeRelacao {

	public void setAnalizadorDeFonteDeDados(
			IAnalisadorDeArquivo analisadorDeFonteDeDados);

	public Relacao getRelacao();

	public void setNomeDaRelacao(String nomeDaRelacao);

	public void criarAtributoNaRelacao(String nomeDoAtributo, long posicao);

	/**
	 * Cria uma ou mais classes na relação.
	 * 
	 * @param nomesDasClasses
	 */
	public void criarClassesNaRelacao(String[] nomesDasClasses);

	/**
	 * Cria uma classe na relação.
	 * 
	 * @param nomeDaClasse
	 */
	public void criarClasseNaRelacao(String nomeDaClasse);

	/**
	 * Cria uma instância na relação.
	 * 
	 * @param valoresDosAtributosDeIntancia
	 */
	public void criarInstaciaNaRelacao(String[] valoresDosAtributosDeIntancia);

}
