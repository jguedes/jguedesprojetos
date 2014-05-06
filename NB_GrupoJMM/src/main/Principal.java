package main;

import java.io.File;

import montadorderelacao.IMontadorDeRelacao;
import montadorderelacao.MontadorDeRelacao;
import relacao.Relacao;
import analisadordearquivo.AnalisadorDeArquivo;
import analisenaivebayes.AnaliseNaiveBayes;

public class Principal {

	public static void main(String[] args) {

		// String string = "5.1,3.5,1.4,0.2,Iris-setosa";
		// Iris iris = TrasformarStringEmIris.transformar(string);
		// System.out.println(iris.toString());
		//
		// // string = "5.1,3.5,1.4,0.2,Iris-setosa\n";

		IMontadorDeRelacao montador = new MontadorDeRelacao();

		new AnalisadorDeArquivo(new File("iris.arff"), montador);

		Relacao relacao = montador.getRelacao();

		AnaliseNaiveBayes analiseNB = new AnaliseNaiveBayes(relacao);

		System.out.println(analiseNB.getProb_APrioriDeClasse(relacao
				.getClasses().get(0)));

		System.out.println(analiseNB.getProb_Condicional(relacao.getAtributos()
				.get(0).getNome(), 5.9, "Iris-virginica"));

	}
}
