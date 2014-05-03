package calculo;

public class Calculo {

	public static double probabilidade(double casosFavoraveis,
			double totalDeCasos) {

		return casosFavoraveis / totalDeCasos;

	}

	public static Double naiveBayes_valorAtributoVazio(int size) {

		return 1.0 / (2.0 * size);

	}

}
