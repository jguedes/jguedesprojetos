package analisadordearquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import relacao.Atributo;
import relacao.Classe;
import relacao.Instacia;
import relacao.Relacao;

public class AnalisadorDeArquivo implements IAnalisadorDeArquivo {

	private Reader arquivo;
	private Relacao relacao;
	private int posicaoDeAtributo;
	private int linhaAtualDoArquivo;
	private String textoDaLinha;

	public AnalisadorDeArquivo(File arquivo, Relacao relacao) {

		try {

			setArquivo(new FileReader(arquivo));

			this.relacao = relacao;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (arquivo != null) {

			analisar();
		}

		System.out.println(relacao.toString());

	}

	@Override
	public void setArquivo(Reader arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public List<Instacia> getlistaDeInstaciasDaRelacaoAnalisada() {

		return relacao.getInstancias();

	}

	@Override
	public List<Classe> getlistaDeClassesDaRelacaoAnalisada() {

		return relacao.getClasses();

	}

	@Override
	public List<Atributo> getlistaDeAtributosDaRelacaoAnalisada() {

		return relacao.getAtributos();

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

					colocarNomeNaRelacao(string);

				} else if (isLinhaDeNomedeAtributo()) {

					string = pegarNomeDoAtributo();

					criarAtributoNaRelacao(string);

				} else if (isLinhaDeNomesDeClasses()) {

					arrayDeString = pegarNomesDasClasses();

					criarClassesNaRelacao(arrayDeString);

				} else {

					// então a linha deve conter uma instância.

					arrayDeString = pegarValoresDeAtributosDeInstancia();

					criarInstaciaNaRelacao(arrayDeString);

				}

			}

			// Ao fim da analise do arquivo deve-se atualizar os valores de
			// distribuição das classes na relação.

			relacao.calcularDistribuicaoDasClasses();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void criarClassesNaRelacao(String[] nomesDeClasse) {

		for (String nome : nomesDeClasse) {

			relacao.addClasse(new Classe(nome));

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

	private void criarAtributoNaRelacao(String nomeDeAtributo) {

		// criar o atributo

		Atributo atributo = new Atributo(nomeDeAtributo);

		// Obs: nos arquivos .arff a ordem de posição dos atributos é
		// estabelecida pela leitura de cima a baixo do arquivo.

		// colocar o atribtuo na relação com sua posição.

		relacao.addAtributo(posicaoDeAtributo, atributo);

		// atualizar posicao para o próximo atributo

		posicaoDeAtributo++;

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

	private void colocarNomeNaRelacao(String nomeDaRelacao) {

		relacao.setNome(nomeDaRelacao);

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

	private void criarInstaciaNaRelacao(String valoresDosAtributosNaInstancia[]) {

		if (!relacao.addInstancia(valoresDosAtributosNaInstancia)) {

			System.out
					.println("Erro ao criar instância com os dados da linha: "
							+ linhaAtualDoArquivo + "\nDados da linha: "
							+ textoDaLinha);

		}

	}

	private String[] pegarValoresDeAtributosDeInstancia() {

		// Nos arquivos .arff cada instância deve estar em uma linha e esta
		// linha deve conter apenas a instância. Os valores dos atributos estão
		// separados por vírgula. Então será criado um array de strings
		// utilizando o síbolo ','.

		String atributosDeInstancia[] = textoDaLinha.split(",");

		return atributosDeInstancia;

	}

	@Override
	public Relacao getRelacao() {

		return relacao;

	}

	@Override
	public String getNomeDaRelacaoAnalisada() {

		return relacao.getNome();

	}

}
