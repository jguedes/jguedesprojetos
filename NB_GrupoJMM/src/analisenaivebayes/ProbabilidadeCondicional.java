package analisenaivebayes;

import relacao.Classe;

public class ProbabilidadeCondicional {

	private String nomeDeAtributo;
	private Object valorDeAtributo;
	private Classe classe;
	private double valorProbabilidadeCondicional;
	private long ocorrencia;

	public String getNomeDeAtributo() {
		return nomeDeAtributo;
	}

	public void setNomeDeAtributo(String nomeDeAtributo) {
		this.nomeDeAtributo = nomeDeAtributo;
	}

	public Object getValorDeAtributo() {
		return valorDeAtributo;
	}

	public void setValorDeAtributo(Object valorDeAtributo) {
		this.valorDeAtributo = valorDeAtributo;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public double getValorProbabilidadeCondicional() {
		return valorProbabilidadeCondicional;
	}

	public void setValorProbabilidadeCondicional(
			double valorProbabilidadeCondicional) {
		this.valorProbabilidadeCondicional = valorProbabilidadeCondicional;
	}

	public void addOcorrencia() {

		this.ocorrencia++;

	}

	public long getOcorrencia() {
		return ocorrencia;
	}

	@Override
	public boolean equals(Object obj) {

		ProbabilidadeCondicional p = (ProbabilidadeCondicional) obj;

		if (this.classe.equals(p.getClasse())
				&& this.nomeDeAtributo.equalsIgnoreCase(p.getNomeDeAtributo())
				&& this.valorDeAtributo.equals(p.getValorDeAtributo())) {

			return true;

		}

		return false;
	}

	public String toString(long ocorrenciasDeClasse) {

		StringBuilder s = new StringBuilder();

		s.append("Probabilidade condicional de [");
		s.append(nomeDeAtributo);
		s.append("(");
		s.append(valorDeAtributo);
		s.append(")/");
		s.append(classe.getNome());
		s.append("]: ");
		s.append(ocorrencia);
		s.append("/");
		s.append(ocorrenciasDeClasse);
		s.append(" = ");
		s.append(valorProbabilidadeCondicional);

		return s.toString();
	}

}
