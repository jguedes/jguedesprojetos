package model.compartilhamento;

import java.util.ArrayList;
import java.util.List;

import model.comentario.Comentario;

public class Compartilhamento_VisualizarEComentar extends
		Compartilhamento_ApenasVisualizar {

	// atributos

	private List<Comentario> comentarios;

	// construtor

	public Compartilhamento_VisualizarEComentar() {
		setPermissao(PERMISSAO_VISUALIZAR_E_COMENTAR);
	}

	// métodos

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public boolean temComentario(String elementoDeDocumento) {
		return (getComentarios() != null);
	}

	public List<Comentario> getComentarios(String elementoDeDocumento) {

		List<Comentario> comentarios = null;

		if (this.comentarios != null) {
			
			for (Comentario c : this.comentarios) {

				if (c.getElementoDeDocumento().equals(elementoDeDocumento)) {

					if (comentarios == null) {

						comentarios = new ArrayList<Comentario>();

					}

					comentarios.add(c);

				}

			}
			
		}
		
		return comentarios;

	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
