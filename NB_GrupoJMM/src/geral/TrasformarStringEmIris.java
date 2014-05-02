package geral;
import java.util.ArrayList;
import java.util.List;

public class TrasformarStringEmIris {

	/**
	 * Recebe uma string no formato "double,double,double,double,string" para
	 * carregar os atributos de uma instância da classe Iris.
	 * 
	 * @param string
	 * @return Iris ou null se a string estiver fora do formato aceito ou se a
	 *         classe não for do conjunto de classes.
	 */
	public static Iris transformar(String string) {

		if (string == null)
			return null;

		String stringSplit[] = string.split(",");

		if (stringSplit.length != 5) {
			System.out
					.println("String para transformar em iris fora do formato aceito: ["
							+ string + "]");
			return null;
		}

		List<String> classes = new ArrayList<String>();

		classes.add("Iris-setosa");
		classes.add("Iris-versicolor");
		classes.add("Iris-virginica");

		if (classes.contains(stringSplit[4])) {

			Iris iris = new Iris();

			iris.setPetallength(Double.parseDouble(stringSplit[0]));
			iris.setPetalwidth(Double.parseDouble(stringSplit[1]));
			iris.setSepallength(Double.parseDouble(stringSplit[2]));
			iris.setSepalwidth(Double.parseDouble(stringSplit[3]));
			iris.setClasse(stringSplit[4]);

			return iris;

		}

		return null;

	}
}
