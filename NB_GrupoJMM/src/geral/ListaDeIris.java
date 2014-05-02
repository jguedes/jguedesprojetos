package geral;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class ListaDeIris extends ArrayList<Iris> {

	private static final long serialVersionUID = 1L;

	public ListaDeIris() {

	}

	public ListaDeIris(String string) {

		criar(string);

	}

	public ListaDeIris(Reader reader) {

		criar(reader);

	}

	private void criar(String array[]) {

		for (String s : array) {

			adicionarIris(TrasformarStringEmIris.transformar(s));

		}

	}

	private void criar(String string) {

		String stringSplit[] = string.split("\n");

		criar(stringSplit);

	}

	private void criar(Reader reader) {

		BufferedReader br = new BufferedReader(reader);

		String textoDaLinha;

		try {

			while ((textoDaLinha = br.readLine()) != null) {

				if (textoDaLinha.equals("@DATA")) {

					while ((textoDaLinha = br.readLine()) != null) {
						if (!textoDaLinha.equals("%")) {
							adicionarIris(TrasformarStringEmIris
									.transformar(textoDaLinha));
						}
					}

					break;

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void adicionarIris(Iris iris) {

		if (iris != null) {

			add(iris);

		}

	}

}
