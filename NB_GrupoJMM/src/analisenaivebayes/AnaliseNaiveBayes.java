package analisenaivebayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import relacao.Atributo;
import relacao.Classe;
import relacao.Instancia;
import relacao.Relacao;
import calculo.Calculo;

public class AnaliseNaiveBayes implements IAnaliseNaiveBayes {

	private Relacao relacao;

	/**
	 * Conjunto de probabilidades a priori das classes da relação.<br>
	 * A chave é o nome da classe e o valor é a probabilidade a priori da
	 * classe.
	 */
	private HashMap<Classe, DadosDeClasse> dadosDeClasse;

	/**
	 * Conjunto das probabilidades condicionais da relação.
	 */
	private List<ProbabilidadeCondicional> probabilidadesCondicionais;

	public AnaliseNaiveBayes(Relacao relacao) {

		setRelacao(relacao);

		carregarDadosDeClasse();

		carregarProbabilidadesCondicionais();

	}

	@Override
	public void setRelacao(Relacao relacao) {

		this.relacao = relacao;

	}

	private void carregarDadosDeClasse() {

		inserirAsClassesNoConjutoDadosDeClasse();

		carregarOcorrenciasDasClasses();

		carregarDistribuicoesDasClasses();

	}

	/**
	 * Insere as classes da relação no conjunto dadosDeClasse.
	 */
	private void inserirAsClassesNoConjutoDadosDeClasse() {

		// Inicializar conjunto de dados de classe.
		dadosDeClasse = new HashMap<Classe, DadosDeClasse>();

		// colocar as classes da relação no conjunto dadosDeClasse

		for (Classe classe : relacao.getClasses()) {

			dadosDeClasse.put(classe, new DadosDeClasse());

		}

	}

	/**
	 * Percorre a lista de intâncias da relação para calcular a quantidade de
	 * ocorrências de cada classe.
	 */
	private void carregarOcorrenciasDasClasses() {

		// Recebe a quantidade de ocorrências de uma classe nas instâncias da
		// relação.

		long ocorrencias;

		// Recebe o nome da classe de uma instância.

		String nomeDaClasseNaInstancia;

		// Percorrer a lista de instâncias da relação para calcular as
		// ocorrências de cada classe.

		for (Instancia instancia : relacao.getInstancias()) {

			// pegar nome da classe na instância

			nomeDaClasseNaInstancia = String.valueOf(instancia
					.getValorDeAtributo("classe"));

			// Procurar pelo item, no conjunto de dados de classe, que contêm a
			// classe correspondente a instância atual.

			for (Classe classe : dadosDeClasse.keySet()) {

				if (classe.getNome().equalsIgnoreCase(nomeDaClasseNaInstancia)) {

					// Atribuir a ocorrências o valor atual de ocorrências
					// da classe e adicionar 1.

					ocorrencias = dadosDeClasse.get(classe).getOcorrencias() + 1;

					// atualizar o valor de ocorrências da classe

					dadosDeClasse.get(classe).setOcorrencias(ocorrencias);

				}

			}

		}

	}

	/**
	 * Define a distribuição de cada classe da relação calculando a
	 * probabilidade a priori de cada classe.
	 */
	private void carregarDistribuicoesDasClasses() {

		// A probabilidade a priori é a divisao dos casos favoráveis pelo total
		// de casos.

		long casosFavoraveis;

		// A quantidade de instâncias na relação é o número total de casos.

		long totalDeCasos = relacao.getInstancias().size();

		// Calcular a distribuição de cada item do conjutno de dados de classe

		for (Classe classe : dadosDeClasse.keySet()) {

			// As ocorrências das classes é número de casos favoráveis.

			casosFavoraveis = dadosDeClasse.get(classe).getOcorrencias();

			// Calcular a probabilidade a priori

			double probabilidade = Calculo.probabilidade(casosFavoraveis,
					totalDeCasos);

			// Atualizar a distribuição do item do conjunto de dados.

			dadosDeClasse.get(classe).setDistribuicao(probabilidade);

		}

	}

	private void carregarProbabilidadesCondicionais() {

		// Inicializar lista de probabilidades condicionais

		probabilidadesCondicionais = new ArrayList<ProbabilidadeCondicional>();

		criarProbabilidadesCondicionais();

		calcularProbCondicionais();

	}

	/**
	 * Percorre a lista de instâncias da relação(como uma busca vertical) para
	 * encontrar a classe.<br>
	 * A cada instância percorre os seus atributos(como uma busca horizontal).<br>
	 * A cada atributo cria uma probabilidade condicional e averigua se ela já
	 * está na lista de probabilidades condicionais.<br>
	 * Se a probabilidade criada ainda não existe adiciona-a a lista.<br>
	 * Atualiza a ocorrência.
	 */
	private void criarProbabilidadesCondicionais() {

		ProbabilidadeCondicional probCond;

		String nomeDoAtributo;

		Classe classe;

		Object valorDoAtributo;

		// Percorrer a lista de instâncias da relação (VERTICAL)

		for (Instancia instancia : relacao.getInstancias()) {

			classe = relacao.getClasseDeInstancia(instancia);

			// Em cada instância percorrer os atributos (HORIZONTAL)

			for (Atributo atributo : relacao.getAtributos()) {

				nomeDoAtributo = atributo.getNome();

				valorDoAtributo = instancia.getValorDeAtributo(nomeDoAtributo);

				if (valorDoAtributoIsFaltante(valorDoAtributo)) {

					valorDoAtributo = Calculo
							.naiveBayes_valorAtributoVazio(relacao
									.getInstancias().size());

				}

				// criar nova probabilidade condicional

				probCond = criarProbabilidadeCondicional(nomeDoAtributo,
						classe, valorDoAtributo);

				// Procurar pelo objeto do tipo ProbabilidadeCondicional onde:
				// o valor do atributo é o corrente para a classe corrente na
				// instância corrente.

				if (!probCondExiste(probCond)) {

					// se não encotrar insere na lista

					// Atualizar ocorrência

					probCond.addOcorrencia();

					probabilidadesCondicionais.add(probCond);

				}

			}

		}

	}

	/**
	 * Calcula a probabilidade condicional de todos os valores de todos os
	 * atributos para todas as classes.
	 */
	private void calcularProbCondicionais() {

		// Probabilidade condicional é divisão da soma das ocorrências do valor
		// de um atributo para uma determinada classe C pela quantidade de
		// ocorrências da classe C no conjunto de instâncias da relação.

		// Recebe a classe a qual o valor do atributo faz referência.
		Classe classe;

		// Recebe a quantidade de ocorrências do valor do atributo para a
		// classe.
		long ocorrValAtrib;

		// Recebe a quantidade de ocorrências da classe no conjunto de
		// instâncias da relação.
		long ocorrClasse;

		// Recebe o resultado da divisão.
		double valorProbCond;

		// Percorrer a lista de probabilidades condicionais

		for (ProbabilidadeCondicional prob : probabilidadesCondicionais) {

			// pegar a classe
			classe = prob.getClasse();

			// pegar a quantidade de ocorrências do valor do atributo.
			ocorrValAtrib = prob.getOcorrencia();

			// pegar a quantidade de ocorrências da classe.
			ocorrClasse = dadosDeClasse.get(classe).getOcorrencias();

			// calcular a probabilidade condicional
			valorProbCond = Calculo.probabilidade(ocorrValAtrib, ocorrClasse);

			// Armazenar no item do conjunto de probabildades condicionais o
			// valor.
			prob.setValorProbabilidadeCondicional(valorProbCond);

			System.out.println(prob.toString(ocorrClasse));
			
		}

	}

	private boolean valorDoAtributoIsFaltante(Object valorDoAtributo) {

		Object valorVazio = "?";

		return valorDoAtributo.equals(valorVazio);

	}

	private ProbabilidadeCondicional criarProbabilidadeCondicional(
			String nomeDoAtributo, Classe classe, Object valorDoAtributo) {

		ProbabilidadeCondicional probCond;

		probCond = new ProbabilidadeCondicional();

		probCond.setClasse(classe);

		probCond.setNomeDeAtributo(nomeDoAtributo);

		probCond.setValorDeAtributo(valorDoAtributo);

		return probCond;

	}

	private boolean probCondExiste(ProbabilidadeCondicional probCond) {

		for (ProbabilidadeCondicional prob : probabilidadesCondicionais) {
			
			if (prob.equals(probCond)) {

				prob.addOcorrencia();

				return true;

			}

		}

		return false;

	}

	@Override
	public double getProb_APrioriDeClasse(Classe classe) {

		return dadosDeClasse.get(classe).getDistribuicao();

	}

	@Override
	public double getProb_APrioriDeClasse(String nomeDaClasse) {

		for (Classe classe : relacao.getClasses()) {

			if (classe.getNome().equalsIgnoreCase(nomeDaClasse)) {

				return getProb_APrioriDeClasse(classe);

			}

		}

		return 0.0;

	}

	@Override
	public double getProb_Condicional(Atributo atributo, Classe classe) {

		return getProb_Condicional(atributo.getNome(), atributo.getValor(),
				classe.getNome());
	}

	@Override
	public double getProb_Condicional(String nomeDeAtributo,
			Object valorDeAtributo, String nomeDaClasse) {

		double valor = 0.0;

		ProbabilidadeCondicional p = new ProbabilidadeCondicional();

		p.setNomeDeAtributo(nomeDeAtributo);
		p.setValorDeAtributo(valorDeAtributo);
		p.setClasse(new Classe(nomeDaClasse));

		for (ProbabilidadeCondicional prob : probabilidadesCondicionais) {
			
			if (prob.equals(p)) {

				valor = prob.getValorProbabilidadeCondicional();

			}

		}

		return valor;

	}

}
