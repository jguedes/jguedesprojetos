package model.usuario;

import java.util.List;

import model.documento.Documento;

public class UsuarioAutor extends Usuario {

	private String senha;

	private List<Documento> documentos;

	public UsuarioAutor() {
		this("", "", "");

	}

	public UsuarioAutor(String nome) {
		this(nome, "", "");

	}

	public UsuarioAutor(String nome, String senha, String email) {

		setNome(nome);

		setSenha(senha);

		setEmail(email);

	}

	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {

		StringBuilder s = new StringBuilder();

		s.append(nome);

		return s.toString();
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}
