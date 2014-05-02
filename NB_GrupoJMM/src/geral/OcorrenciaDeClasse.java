package geral;
import java.util.HashMap;

import relacao.Atributo;

public class OcorrenciaDeClasse {

	private HashMap<String, Atributo> atributos;

	public OcorrenciaDeClasse() {

	}

	public OcorrenciaDeClasse(HashMap<String, Atributo> atributos) {
		this.atributos = atributos;
	}

	public HashMap<String, Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(HashMap<String, Atributo> atributos) {
		this.atributos = atributos;
	}
}
