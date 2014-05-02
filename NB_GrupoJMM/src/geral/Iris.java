package geral;
public class Iris {
	private double sepallength;
	private double sepalwidth;
	private double petallength;
	private double petalwidth;
	private String classe;

	public Iris(double sepallength, double sepalwidth, double petallength,
			double petalwidth, String classe) {
		this.sepallength = sepallength;
		this.sepalwidth = sepalwidth;
		this.petallength = petallength;
		this.petalwidth = petalwidth;
		this.classe = classe;
	}

	public Iris() {

	}

	public double getSepallength() {
		return sepallength;
	}

	public void setSepallength(double sepallength) {
		this.sepallength = sepallength;
	}

	public double getSepalwidth() {
		return sepalwidth;
	}

	public void setSepalwidth(double sepalwidth) {
		this.sepalwidth = sepalwidth;
	}

	public double getPetallength() {
		return petallength;
	}

	public void setPetallength(double petallength) {
		this.petallength = petallength;
	}

	public double getPetalwidth() {
		return petalwidth;
	}

	public void setPetalwidth(double petalwidth) {
		this.petalwidth = petalwidth;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {

		StringBuilder s = new StringBuilder();

		s.append("-----------------------------------------------------------------------------------------------------------------------\n");
		s.append("Iris: ");
		s.append("sepal length = ");
		s.append(sepallength);
		s.append(" | sepal width = ");
		s.append(sepalwidth);
		s.append(" | petal length = ");
		s.append(petallength);
		s.append(" | petal width = ");
		s.append(petalwidth);
		s.append(" | class = ");
		s.append(classe);
		s.append("\n-----------------------------------------------------------------------------------------------------------------------");

		return s.toString();
	}
}
