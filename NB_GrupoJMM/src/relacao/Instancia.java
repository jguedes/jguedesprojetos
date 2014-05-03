package relacao;

import java.util.HashMap;

public class Instancia {

	/**
	 * Conjunto dos atributos da instância.<br>
	 * <br>
	 * A chave é o nome do atributo. O valor é do tipo Object.
	 */
	private HashMap<String, Object> atributosDeInstancia;

	/**
	 * O conjunto de valores dos atributos são armazenados em HashMap onde a
	 * chave é o nome do atributo. O valor associado a chave do HashMap é
	 * Object.
	 * 
	 * @param nomesDosAtributos
	 */
	public Instancia(String[] nomesDosAtributos) {

		atributosDeInstancia = new HashMap<String, Object>();

		criarAtributosDeInstancia(nomesDosAtributos);

	}

	/**
	 * O conjunto de valores dos atributos são armazenados em HashMap onde a
	 * chave é o nome do atributo. O valor associado a chave do HashMap é
	 * Object.
	 * 
	 * @param nomeDeAtributo
	 * @param valor
	 */
	public void setValorDeAtributo(String nomeDeAtributo, Object valor) {

		atributosDeInstancia.put(nomeDeAtributo, valor);

	}

	/**
	 * Recebe o nome do atributo e retorna o seu valor.<br>
	 * <br>
	 * O conjunto de valores dos atributos são armazenados em HashMap onde a
	 * chave é o nome do atributo. O valor associado a chave do HashMap é
	 * Object.
	 * 
	 * @param nomeDeAtributo
	 * @return Object
	 */
	public Object getValorDeAtributo(String nomeDeAtributo) {

		return atributosDeInstancia.get(nomeDeAtributo);

	}

	private void criarAtributosDeInstancia(String[] nomesDosAtributos) {

		for (String nomeDeAtributo : nomesDosAtributos) {

			atributosDeInstancia.put(nomeDeAtributo, null);

		}

	}

	@Override
	public String toString() {

		StringBuilder s = new StringBuilder();

		for (String key : atributosDeInstancia.keySet()) {

			s.append(atributosDeInstancia.get(key));

			s.append(",");

		}

		return s.toString();

	}

}
