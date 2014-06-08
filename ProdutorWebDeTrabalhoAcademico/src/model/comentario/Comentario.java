package model.comentario;

import java.sql.Timestamp;

public class Comentario {

	private int id;

	/**
	 * O elemento do documento pode ser capa, introducao, desenvolvimento ou
	 * conclusao.
	 */
	private String elementoDoDocumento;

	private String texto;
	private boolean resolvido;
	private Timestamp dataPostagem;
	private Timestamp dataResolvido;

	public Comentario() {

	}

	public boolean isResolvido() {
		return resolvido;
	}

	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}

	public Timestamp getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Timestamp dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Timestamp getDataResolvido() {
		return dataResolvido;
	}

	public void setDataResolvido(Timestamp dataResolvido) {
		this.dataResolvido = dataResolvido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getElementoDeDocumento() {
		return elementoDoDocumento;
	}

	public void setElementoDoDocumento(String elementoDoDocumento) {
		this.elementoDoDocumento = elementoDoDocumento;
	}

}
