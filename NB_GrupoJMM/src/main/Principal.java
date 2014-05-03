package main;

import java.io.File;

import montadorderelacao.IMontadorDeRelacao;
import montadorderelacao.MontadorDeRelacao;
import analisadordearquivo.AnalisadorDeArquivo;
import analisadordearquivo.IAnalisadorDeArquivo;
import analisenaivebayes.AnaliseNaiveBayes;
import relacao.Relacao;

public class Principal {

	public static void main(String[] args) {

		// String string = "5.1,3.5,1.4,0.2,Iris-setosa";
		// Iris iris = TrasformarStringEmIris.transformar(string);
		// System.out.println(iris.toString());
		//
		// // string = "5.1,3.5,1.4,0.2,Iris-setosa\n";

		IMontadorDeRelacao montador = new MontadorDeRelacao();

		IAnalisadorDeArquivo analisador = new AnalisadorDeArquivo(new File(
				"iris.arff"), montador);

		Relacao relacao = montador.getRelacao();

		AnaliseNaiveBayes analiseNB = new AnaliseNaiveBayes(relacao);

		System.out.println(analiseNB.getProb_APrioriDeClasse(relacao
				.getClasses().get(0)));
		System.out.println(analiseNB.getProb_Condicional(relacao.getAtributos()
				.get(0).getNome(), 5.1, "Iris-setosa"));
	}
}
