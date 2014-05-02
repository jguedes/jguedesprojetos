package main;

import java.io.File;

import montadorderelacao.IMontadorDeRelacao;
import montadorderelacao.MontadorDeRelacao;
import analisadordearquivo.AnalisadorDeArquivo;
import analisadordearquivo.IAnalisadorDeArquivo;
import analisederelacao.AnaliseDeRelacao;
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

		AnaliseDeRelacao analise = new AnaliseDeRelacao();

		analise.setRelacao(relacao);

		System.out.println(analise.getProb_APrioriDeClasse(relacao.getClasses()
				.get(0)));
	}
}
