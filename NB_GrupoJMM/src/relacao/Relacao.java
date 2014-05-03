package relacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Relacao {

	/**
	 * Nome da relação.
	 */
	private String nome;

	/**
	 * Conjunto de classes da relação.
	 */
	private List<Classe> classes;

	/**
	 * Conjunto de atributos da relação.<br>
	 * <br>
	 * A chave é a posição do atributo na instância.
	 */
	private HashMap<Long, Atributo> atributos;

	/**
	 * Conjunto de instâncias da relação.
	 */
	private List<Instancia> instancias;

	public Relacao() {
		this(null);
	}

	public Relacao(String nome) {

		this.nome = nome;
		classes = new ArrayList<Classe>();
		atributos = new HashMap<Long, Atributo>();
		instancias = new ArrayList<Instancia>();

	}

	public void setNome(String nome) {
		this.nome = nome;

	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void addClasse(Classe classe) {

		classes.add(classe);

	}

	public void addAtributo(long posicao, Atributo atributo) {

		atributos.put(posicao, atributo);

	}

	/**
	 * Recebe um array de strings contendo os valores dos atributos de
	 * instância.<br>
	 * Valida os dados do array.<br>
	 * Cria uma instância com os atributos da relação.<br>
	 * Passa os valores do array para os seus respectivos atributos na
	 * instância.<br>
	 * Incrementa a ocorrência da classe da instância.<br>
	 * Insere a intância na lista de instância da relação.
	 * 
	 * @param valoresDosAtributos
	 * @return
	 */
	public boolean addInstancia(String[] valoresDosAtributos) {

		if (validarInstânciaEmArrayDeString(valoresDosAtributos)) {

			return false;

		}

		Instancia instancia = novaInstancia();

		colocarOsValoresNaInstancia(instancia, valoresDosAtributos);

		instancias.add(instancia);

		return true;

	}

	private void colocarOsValoresNaInstancia(Instancia instancia,
			String[] valoresDosAtributos) {

		// calcular o index da última posição do array de string com os valores
		// dos atributos para colocar na última posição array o valor da classe.

		int ultimaPosicaoDoArray = valoresDosAtributos.length - 1;

		// atribuir valores dos atributos

		for (long key : atributos.keySet()) {

			instancia.setValorDeAtributo(atributos.get(key).getNome(),
					valoresDosAtributos[(int) key]);

		}

		// atribuir o valor da classe

		instancia.setValorDeAtributo("classe",
				valoresDosAtributos[ultimaPosicaoDoArray]);

	}

	/**
	 * Cria uma instância de acordo com os atributos da relação.<br>
	 * Obs.: Os atributos de instâncias são os atributos da relação mais o
	 * atributo CLASSE.
	 * 
	 * @return
	 */
	private Instancia novaInstancia() {

		int size = getQuantidadeDeAtributosDeInstacia();

		String[] nomesDosAtributosDeInstancia = new String[size];

		// colocar o nome dos atributos da relação no array de string

		for (long key : atributos.keySet()) {

			nomesDosAtributosDeInstancia[(int) key] = atributos.get(key)
					.getNome();

		}

		// colocar o nome "classe" na última posição do array

		nomesDosAtributosDeInstancia[size - 1] = "classe";

		// criar a instância

		Instancia instanciaNova = new Instancia(nomesDosAtributosDeInstancia);

		return instanciaNova;

	}

	/**
	 * Averiguar o tamanho do array de string. Se corresponde com a quantidade
	 * de atributos de instância da ralação. Se não corresponder o array não
	 * contém uma instância válida para a relação.
	 * 
	 * @param arrayDeString
	 * @return
	 */
	private boolean validarInstânciaEmArrayDeString(String[] arrayDeString) {

		return arrayDeString.length != getQuantidadeDeAtributosDeInstacia();

	}

	@Override
	public String toString() {

		StringBuilder s = new StringBuilder();
		s.append("Relação: ");
		s.append(nome);
		s.append("\n Classes: { ");
		for (Classe c : classes) {
			s.append(c.getNome());
			s.append(" | ");
		}
		s.append(" }\nAtributos: { ");
		for (Long posicao : atributos.keySet()) {
			s.append(atributos.get(posicao).getNome());
			s.append(" | ");
		}
		s.append(" }\nInstâncias:\n");
		for (Instancia i : instancias) {
			s.append(i.toString());
			s.append("\n");
		}

		return s.toString();

	}

	public String getNome() {
		return nome;
	}

	/**
	 * Retorna a quantidade de atributos + 1. Por causa do campo que contêm a
	 * classe na instância.
	 * 
	 * @return
	 */
	public int getQuantidadeDeAtributosDeInstacia() {

		int quantidade = atributos.size() + 1;

		return quantidade;

	}

	public List<Instancia> getInstancias() {

		return instancias;

	}

	public List<Atributo> getAtributos() {

		List<Atributo> lista = new ArrayList<Atributo>();

		for (long key : atributos.keySet()) {

			lista.add(atributos.get(key));

		}

		return lista;

	}

	public Classe getClasseDeInstancia(Instancia instancia) {

		String nomeDaClasseNaInstancia = String.valueOf(instancia
				.getValorDeAtributo("classe"));

		for (Classe classe : classes) {

			if (classe.getNome().equalsIgnoreCase(nomeDaClasseNaInstancia)) {

				return classe;

			}

		}

		return null;

	}
}
