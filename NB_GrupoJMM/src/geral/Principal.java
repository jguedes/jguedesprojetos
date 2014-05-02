package geral;

import java.io.File;

import analisederelacao.AnaliseDeRelacao;
import relacao.Relacao;

public class Principal {

	public static void main(String[] args) {

		// String string = "5.1,3.5,1.4,0.2,Iris-setosa";
		// Iris iris = TrasformarStringEmIris.transformar(string);
		// System.out.println(iris.toString());
		//
		// // string = "5.1,3.5,1.4,0.2,Iris-setosa\n";

		Relacao relacao = new Relacao(new File("iris.arff"));

		AnaliseDeRelacao analise = new AnaliseDeRelacao();
		
		analise.setRelacao(relacao);

		System.out.println(analise.getProb_APrioriDeClasse(relacao.getClasses()
				.get(0)));
	}

}
