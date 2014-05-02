package montadorderelacao;

import relacao.Atributo;
import relacao.Classe;
import relacao.Relacao;
import analisadordearquivo.IAnalisadorDeArquivo;

public class MontadorDeRelacao implements IMontadorDeRelacao {

	private Relacao relacao;
	private IAnalisadorDeArquivo analisadorDeFonteDeDados;

	@Override
	public void setAnalizadorDeFonteDeDados(
			IAnalisadorDeArquivo analisadorDeFonteDeDados) {

		this.analisadorDeFonteDeDados = analisadorDeFonteDeDados;

	}

	@Override
	public Relacao getRelacao() {

		return relacao;

	}

	@Override
	public void setNomeDaRelacao(String nomeDaRelacao) {

		relacao.setNome(nomeDaRelacao);

	}

	@Override
	public void criarAtributoNaRelacao(String nomeDoAtributo, long posicao) {

		// criar o atributo

		Atributo atributo = new Atributo(nomeDoAtributo);

		// Obs: nos arquivos .arff a ordem de posição dos atributos é
		// estabelecida pela leitura de cima a baixo do arquivo.

		// colocar o atribtuo na relação com sua posição.

		relacao.addAtributo(posicao, atributo);

	}

	@Override
	public void criarClassesNaRelacao(String[] nomesDasClasses) {

		for (String nome : nomesDasClasses) {

			criarClasseNaRelacao(nome);

		}

	}

	@Override
	public void criarClasseNaRelacao(String nomeDaClasse) {

		relacao.addClasse(new Classe(nomeDaClasse));

	}

	@Override
	public void criarInstaciaNaRelacao(String[] valoresDosAtributosDeIntancia) {

		if (!relacao.addInstancia(valoresDosAtributosDeIntancia)) {

			System.out.println("Montador de Relação: Erro ao criar instância");

		}

	}

}
