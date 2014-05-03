package analisadordearquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import montadorderelacao.IMontadorDeRelacao;

public class AnalisadorDeArquivo implements IAnalisadorDeArquivo {

	private Reader arquivo;
	private int posicaoDeAtributo;
	private int linhaAtualDoArquivo;
	private String textoDaLinha;
	private IMontadorDeRelacao montadorDeRelacao;

	public AnalisadorDeArquivo(File arquivo,
			IMontadorDeRelacao montadorDeRelacao) {

		try {

			setArquivo(new FileReader(arquivo));

			setMontadorDeRelacao(montadorDeRelacao);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (arquivo != null) {

			analisar();

		}

	}

	@Override
	public void setArquivo(Reader arquivo) {

		this.arquivo = arquivo;

	}

	@Override
	public void setMontadorDeRelacao(IMontadorDeRelacao montadorDeRelacao) {

		this.montadorDeRelacao = montadorDeRelacao;

	}

	private void analisar() {

		String string, arrayDeString[];

		BufferedReader br = new BufferedReader(arquivo);

		posicaoDeAtributo = 0;

		try {

			while ((textoDaLinha = br.readLine()) != null) {

				linhaAtualDoArquivo++;

				if (desprezarLinha()) {

				} else if (isLinhaDoNomeDaRelacao()) {

					string = pegarNomeDaRelacao();

					montadorDeRelacao.setNomeDaRelacao(string);

				} else if (isLinhaDeNomedeAtributo()) {

					string = pegarNomeDoAtributo();

					montadorDeRelacao.criarAtributoNaRelacao(string,
							posicaoDeAtributo);

					posicaoDeAtributo++;

				} else if (isLinhaDeNomesDeClasses()) {

					arrayDeString = pegarNomesDasClasses();

					montadorDeRelacao.criarClassesNaRelacao(arrayDeString);

				} else {

					// então a linha deve conter uma instância.

					arrayDeString = pegarValoresDeAtributosDeInstancia();

					montadorDeRelacao.criarInstaciaNaRelacao(arrayDeString);

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				br.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	private String[] pegarNomesDasClasses() {

		String nomesDasClasses[];

		String aux;

		// Nos arquivos .arff as classes estão entre chaves e ainda os nomes de
		// classes que têm mais de uma palavra estão entre aspas simples.

		// Trocando o símbolos '{' e '}' por '&'. Principal motivo: bug com o
		// método split().

		aux = textoDaLinha.replaceAll("[{]|[}]", "&");

		// criando um array de strings utilizando o caracter '&'. Os nomes de
		// classes devem estar na string da segunda posição do array.

		aux = aux.split("&")[1];

		// retirar possíveis aspas simples

		aux = aux.replace("'", "");

		// criando um array de strings utilizando o caracter ','. Cada posicao
		// deste array deve conter o nome de uma classe de relação.

		nomesDasClasses = aux.split(",");

		return nomesDasClasses;

	}

	private String pegarNomeDoAtributo() {

		String nomeDeAtributo;

		String aux;

		// pegar a substring após substring @ATTRIBUTE

		aux = textoDaLinha.substring("@ATTRIBUTE".length() - 1);

		// Por padrão nos arquivos .arff os nomes dos atributos estão entre
		// aspas simples. Então cria-se um array de strings utilizando as aspas
		// simples. O nome do atributo deve estar na segunda posição do array.

		aux = aux.split("'")[1];

		// retirar possíveis espaços antes ou depois da string.

		nomeDeAtributo = aux.trim();

		return nomeDeAtributo;

	}

	private boolean isLinhaDeNomesDeClasses() {
		return textoDaLinha.startsWith("@ATTRIBUTE")
				&& (textoDaLinha.contains("'class'") || textoDaLinha
						.contains("'type'"));
	}

	private boolean isLinhaDeNomedeAtributo() {
		return textoDaLinha.startsWith("@ATTRIBUTE")
				&& (!textoDaLinha.contains("'class'") && !textoDaLinha
						.contains("'type'"));
	}

	private String pegarNomeDaRelacao() {

		String aux;

		// pegar a substring após a substring @RELATION

		aux = textoDaLinha.substring("@RELATION".length());

		// talvez o nome da relação esteja entre aspas simples. Então retira-se
		// estas.

		aux = aux.replace("'", "");

		return aux.trim();

	}

	private boolean isLinhaDoNomeDaRelacao() {
		return textoDaLinha.startsWith("@RELATION");
	}

	private boolean desprezarLinha() {

		boolean desprezar;

		// Nos arquivos .arff as linhas que iniciam com o síbolo '%' são apenas
		// comentários.

		boolean isComentario = textoDaLinha.startsWith("%");

		// Nos arquivos .arff tem uma linha que deve ser desprezada ela inicia
		// com a palavra @data que pode estar em caixa-alta ou não. Esta linha
		// inidica o início das instâncias.

		boolean isArrobaData = textoDaLinha.toLowerCase().startsWith("@data");

		// Nos arquivos .arff existem linhas em branco

		boolean isLinhaEmBranco = textoDaLinha.length() == 0;

		desprezar = isComentario || isArrobaData || isLinhaEmBranco;

		return desprezar;

	}

	private String[] pegarValoresDeAtributosDeInstancia() {

		// Nos arquivos .arff cada instância deve estar em uma linha e esta
		// linha deve conter apenas a instância. Os valores dos atributos estão
		// separados por vírgula. Então será criado um array de strings
		// utilizando o síbolo ','.

		String atributosDeInstancia[] = textoDaLinha.split(",");

		return atributosDeInstancia;

	}

}
