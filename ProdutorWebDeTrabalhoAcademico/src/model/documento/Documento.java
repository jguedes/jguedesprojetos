package model.documento;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.compartilhamento.Compartilhamento;

public class Documento {
	private int id;
	private String titulo = "título";
	private String instituicao = "instituição";
	private String localdata = "localdata";
	private String introducao = "introdução";
	private String desenvolvimento = "desenvolvimento";
	private String conclusao = "conclusão";
	private List<Compartilhamento> compartilhamentos;
	private Timestamp dataCadastro;

	public Documento() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Compartilhamento> getCompartilhamentos() {
		if (compartilhamentos == null) {
			compartilhamentos = new ArrayList<Compartilhamento>();
		}
		return compartilhamentos;
	}

	public void setCompartilhamentos(List<Compartilhamento> compartilhamentos) {
		this.compartilhamentos = compartilhamentos;
	}

	@Override
	public String toString() {

		return this.titulo;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;

	}

	public boolean isCompartilhado() {

		if (compartilhamentos == null)
			return false;

		if (compartilhamentos.size() == 0)
			return false;

		// é o auto-compartilhamento
		if (compartilhamentos.size() == 1)
			return false;

		return true;

	}

	public String getLocaldata() {
		return localdata;
	}

	public void setLocaldata(String localdata) {
		this.localdata = localdata;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getIntroducao() {
		return introducao;
	}

	public void setIntroducao(String introducao) {
		this.introducao = introducao;
	}

	public String getDesenvolvimento() {
		return desenvolvimento;
	}

	public void setDesenvolvimento(String desenvolvimento) {
		this.desenvolvimento = desenvolvimento;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

}
